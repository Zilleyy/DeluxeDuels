package com.prophaze.luxduels.util;

import com.prophaze.luxduels.util.world.LocationUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public Iterator<Block> blockList() {
        List<Block> bL = new ArrayList<>(this.getTotalBlockSize());
        for(int x = (int)this.min.getX(); x <= (int)this.max.getX(); ++x) {
            for(int y = (int)this.min.getY(); y <= (int)this.max.getY(); ++y) {
                for(int z = (int)this.min.getZ(); z <= (int)this.max.getZ(); ++z) {
                    final Block b = Bukkit.getWorld(worldName).getBlockAt(x, y, z);
                    bL.add(b);
                    return bL.iterator();
                }
            }
        }
        return bL.iterator();
    }

    public int getTotalBlockSize() {
        return this.getHeight() * this.getXWidth() * this.getZWidth();
    }

    public int getHeight() {
        return (int)(this.max.getY() - this.min.getY() + 1);
    }

    public int getXWidth() {
        return (int)(this.max.getX() - this.min.getX() + 1);
    }

    public int getZWidth() {
        return (int)(this.max.getZ() - this.min.getZ() + 1);
    }

}
