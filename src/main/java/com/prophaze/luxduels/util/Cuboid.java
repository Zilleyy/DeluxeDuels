package com.prophaze.luxduels.util;

import com.prophaze.luxduels.util.item.LocationUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class Cuboid {

    @Getter private final String worldName;
    @Getter private final Vector minV, maxV;
    @Getter private final Location min, max;

    public Cuboid(Location min, Location max) {
        this.worldName = min.getWorld().getName();

        this.min = min;
        this.max = max;

        this.minV = new Vector(Math.min(min.getX(), max.getX()), Math.min(min.getY(), max.getY()), Math.min(min.getZ(), max.getZ()));
        this.maxV = new Vector(Math.max(min.getX(), max.getX()), Math.max(min.getY(), max.getY()), Math.max(min.getZ(), max.getZ()));
    }

    public Location getCenter() {
        final int x1 = (int)max.getX() + 1;
        final int y1 = (int)max.getY() + 1;
        final int z1 = (int)max.getZ() + 1;
        return new Location(Bukkit.getWorld(worldName), min.getX() + (x1 - min.getX()) / 2.0, min.getY() + (y1 - min.getY()) / 2.0, min.getZ() + (z1 - min.getZ()) / 2.0);
    }

    public boolean containsLocation(Location location) {
        return location.toVector().isInAABB(minV, maxV);
    }

    @Override
    public String toString() {
        return LocationUtil.locationToString(min, false) + "," + LocationUtil.locationToString(max, true);
    }

    public static Cuboid fromString(String string) {
        String[] split = string.split(",");
        Location min = new Location(Bukkit.getWorld(split[6]), Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]));
        Location max = new Location(Bukkit.getWorld(split[6]), Double.parseDouble(split[3]), Double.parseDouble(split[4]), Double.parseDouble(split[5]));
        return new Cuboid(min, max);
    }

}
