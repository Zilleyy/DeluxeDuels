package com.prophaze.luxduels.util.world;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtil {

    public static String locationToString(Location location, boolean addWorldName) {
        String toString = location.getX() + "," + location.getY() + "," + location.getZ();
        if(addWorldName) toString += "," + location.getWorld().getName();
        return toString;
    }

    public static Location stringToLocation(String string) {
        String[] split = string.split(",");
        return new Location(Bukkit.getWorld(split[3]), Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
    }

}
