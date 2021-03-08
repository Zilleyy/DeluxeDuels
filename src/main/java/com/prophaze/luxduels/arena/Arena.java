package com.prophaze.luxduels.arena;

import com.prophaze.luxduels.LuxDuels;
import com.prophaze.luxduels.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;
import java.util.UUID;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 9:58 am AEST
 */
public class Arena {

    /* Identifiers */
    private String name;

    /* Location */
    private String world;
    private int x, y, z;

    private List<Profile> members;

    /**
     * Constructor for Arena, should only be called from the ArenaManager.
     * @param name
     * @param location
     */
    protected Arena(String name, Location location) {
        this.name = name;

        this.world = location.getWorld().getName();
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
    }

    public String getName() {
        return this.name;
    }

    public Location getLocation() {
        return new Location(this.getWorld(), this.x, this.y, this.z);
    }

    public World getWorld() {
        return Bukkit.getWorld(this.world);
    }

    public String getWorldName() {
        return this.world;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public void addMember(Profile profile) {
        this.members.add(profile);
    }

    public void addMember(UUID uuid) {
        this.members.add(LuxDuels.getInstance().getProfileManager().getProfileByUUID(uuid));
    }

}
