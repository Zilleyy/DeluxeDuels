package com.prophaze.luxduels.queue;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.prophaze.luxduels.arena.Arena;
import com.prophaze.luxduels.arena.ArenaManager;
import com.prophaze.luxduels.kits.Kit;
import com.prophaze.luxduels.match.Match;
import com.prophaze.luxduels.match.MatchManager;
import com.prophaze.luxduels.profile.Profile;
import com.prophaze.luxduels.profile.ProfileManager;
import com.prophaze.luxduels.util.Schematics;
import com.prophaze.luxduels.util.item.Items;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FilenameUtils;
import org.bukkit.inventory.Inventory;

import java.util.LinkedList;
import java.util.Map;

public class Queue {

    private static final Map<Kit, LinkedList<Profile>> queue = Maps.newHashMap();

    public static void addProfile(Kit kit, Profile profile) {
        LinkedList<Profile> list;
        if(queue.containsKey(kit)) list = queue.get(kit);
        else list = Lists.newLinkedList();

        list.add(profile);
        queue.put(kit, list);

        ProfileManager.saveInventory(profile);
        clearInventory(profile);
        applyInventory(profile);
    }

    public static void removeProfile(Profile profile) {
        if(!inQueue(profile)) return;

        LinkedList<? extends Profile> list = queue.get(getTypeOf(profile));
        list.remove(profile);

        clearInventory(profile);
        ProfileManager.giveSavedInventory(profile);
    }

    private static void applyInventory(Profile profile) {
        Inventory inventory = profile.getPlayer().getInventory();
        inventory.setItem(8, Items.LEAVE_QUEUE);
    }

    private static void clearInventory(Profile profile) {
        profile.getPlayer().getInventory().clear();
    }

    public static boolean inQueue(Profile profile) {
        for(LinkedList<Profile> list : queue.values()) {
            for(Profile queued : list) {
                if(queued.equals(profile)) return true;
            }
        }
        return false;
    }

    public static Kit getTypeOf(Profile profile) {
        if(!inQueue(profile)) return null;
        for (Kit type : queue.keySet()) {
            if(queue.get(type).contains(profile)) return type;
        }
        return null;
    }

    public static int getSize(Kit type) {
        return queue.get(type).size();
    }

    public static int getPositionOf(Profile profile) {
        if(!inQueue(profile)) return -1;
        for(Kit kit : queue.keySet()) {
            for(Profile queued : queue.get(kit)) {
                if(queued.equals(profile)) return queue.get(kit).indexOf(profile) + 1;
            }
        }
        return -1;
    }

    /**
     * Use instead of null checking next(kit).
     * @param kit
     * @return
     */
    public static boolean hasNext(Kit kit) {
        if(queue.get(kit) == null) return false;
        if(queue.get(kit).size() < 2) return false;
        return true;
    }

    public static Match next(Kit kit) {
        if(queue.get(kit) == null) return null;
        if(queue.get(kit).size() < 2) return null;
        Profile p1, p2;
        p1 = queue.get(kit).get(0);
        p2 = queue.get(kit).get(1);
        queue.get(kit).remove(p1);
        queue.get(kit).remove(p2);
        String schemName = FilenameUtils.removeExtension(Schematics.getRandomSchematic());
        if(ArenaManager.getVacant(schemName) == null) {
            Arena arena = ArenaManager.createAndGet(schemName);
            arena.setGeneratingMap(true);
            return MatchManager.createAndGet(arena, kit, p1, p2);
        } else {
            return MatchManager.createAndGet(ArenaManager.getVacant(schemName), kit, p1, p2);
        }
    }

}
