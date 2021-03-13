package com.prophaze.luxduels.queue;

import com.prophaze.luxduels.Constant;
import com.prophaze.luxduels.arena.ArenaManager;
import com.prophaze.luxduels.match.Match;
import com.prophaze.luxduels.match.MatchManager;
import com.prophaze.luxduels.match.MatchType;
import com.prophaze.luxduels.profile.Profile;
import com.prophaze.luxduels.profile.ProfileManager;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.LinkedList;

public class Queue {

    private static HashMap<MatchType, LinkedList<Profile>> queue = new HashMap<>();

    public static void addProfile(MatchType matchType, Profile profile) {
        LinkedList<Profile> list;
        if(queue.containsKey(matchType)) list = queue.get(matchType);
        else list = new LinkedList<>();

        list.add(profile);
        queue.put(matchType, list);

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
        inventory.setItem(8, Constant.LEAVE_QUEUE);
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

    public static MatchType getTypeOf(Profile profile) {
        if(!inQueue(profile)) return null;
        for (MatchType type : queue.keySet()) {
            if(queue.get(type).contains(profile)) return type;
        }
        return null;
    }

    public static int getSize(MatchType type) {
        return queue.get(type).size();
    }

    public static int getPositionOf(Profile profile) {
        if(!inQueue(profile)) return -1;
        for(MatchType type : queue.keySet()) {
            for(Profile queued : queue.get(type)) {
                if(queued.equals(profile)) return queue.get(type).indexOf(profile) + 1;
            }
        }
        return -1;
    }

    /**
     * Use instead of null checking next(MatchType).
     * @param matchType
     * @return
     */
    public static boolean hasNext(MatchType matchType) {
        if(queue.get(matchType) == null) return false;
        if(queue.get(matchType).size() < 2) return false;
        return true;
    }

    public static Match next(MatchType matchType) {
        if(queue.get(matchType) == null) return null;
        if(queue.get(matchType).size() < 2) return null;
        Match returnMatch = null;
        Profile p1, p2;
        p1 = queue.get(matchType).get(0);
        p2 = queue.get(matchType).get(1);
        queue.get(matchType).remove(p1);
        queue.get(matchType).remove(p2);
        if(ArenaManager.getVacant() == null) {
            returnMatch = MatchManager.createAndGet(ArenaManager.createAndGet(), matchType, p1, p2);
        } else {
            returnMatch = MatchManager.createAndGet(ArenaManager.getVacant(), matchType, p1, p2);
        }
        return returnMatch;
    }

}
