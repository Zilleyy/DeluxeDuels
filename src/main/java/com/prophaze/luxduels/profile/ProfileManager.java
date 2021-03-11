package com.prophaze.luxduels.profile;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * Author: Zilleyy, ProPhaze
 * <br>
 * Date: 9/03/2021 @ 9:57 am AEST
 */
public class ProfileManager {

    private static List<Profile> profiles = new ArrayList<>();

    // Optional<?>#findAny()#get() might throw a NullPointerException if it didn't find anything I'm not sure...
    public static Profile getProfile(UUID uuid) {
        return profiles.stream().filter(profile -> profile.getUUID().equals(uuid)).findAny().get();
    }

    public static Profile getProfile(Player player) {
        return getProfile(player.getUniqueId());
    }

    public static void loadProfiles() {
        Bukkit.getOnlinePlayers().forEach(player -> ProfileManager.loadProfile(player.getUniqueId()));
    }

    public static void loadProfile(UUID uuid) {
        Profile profile = new Profile(uuid);
        if(profile.getFile().getString(uuid.toString() + ".stats") != null) {
            profile.setPlayerStats(new Statistics(profile));
        } else profile.setPlayerStats(new Statistics());
        profiles.add(profile);
    }

    public static void loadProfile(Player player) {
        loadProfile(player.getUniqueId());
    }

    private static void addProfile(Profile profile) {
        profiles.add(profile);
    }

    private static void removeProfile(Profile profile) {
        profiles.remove(profile);
    }

    /**
     * Save the inventory of the player before they join a match so their items can be returned to them.
     * @param profile
     */
    public static void saveInventory(Profile profile) {
        HashMap<Integer, ItemStack> savedInventory = new HashMap<>();

        Inventory inventory = profile.getPlayer().getInventory();
        for(int slot = 0; slot < inventory.getSize(); slot++) {
            savedInventory.put(slot, inventory.getItem(slot));
        }
        profile.setSavedInventory(savedInventory);
    }

    public static void giveSavedInventory(Profile profile) {
        Inventory inventory = profile.getPlayer().getInventory();
        for(Map.Entry<Integer, ItemStack> entry : profile.getSavedInventory().entrySet()) {
            if(entry.getValue() == null) continue;
            inventory.setItem(entry.getKey(), entry.getValue());
        }
    }

}
