package com.prophaze.luxduels.arena;

import com.prophaze.luxduels.match.Match;
import com.prophaze.luxduels.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

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

}
