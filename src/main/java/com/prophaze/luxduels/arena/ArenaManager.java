package com.prophaze.luxduels.arena;

import com.boydti.fawe.FaweAPI;
import com.google.common.collect.Lists;
import com.prophaze.luxduels.LuxDuels;
import com.prophaze.luxduels.file.Yaml;
import com.prophaze.luxduels.kits.Kit;
import com.prophaze.luxduels.util.Cuboid;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.math.BlockVector3;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Author: Zilleyy, ProPhaze
 * <br>
 * Date: 9/03/2021 @ 9:58 am AEST
 */
public class ArenaManager {

    private static final Yaml file = LuxDuels.getInstance().getArenaFile();

    private static final List<Arena> arenas = Lists.newArrayList();

    private static final World world = Bukkit.getWorld("world");

    @Getter @Setter private static int last = 1000;

    /**
     * @param uuid The uuid used to identify the arena.
     * @return either the arena matching the name, or null if nothing matches.
     */
    public static Arena getArenaByUUID(UUID uuid) {
        for(Arena arena : arenas) {
            if(arena.getUUID().equals(uuid)) return arena;
        }
        return null;
    }

    public static Arena getArenaContaining(Location location) {
        for(Arena arena : arenas) {
            if(arena.getCuboid().containsLocation(location)) return arena;
        }
        return null;
    }

    public static Arena getVacant() {
        for(Arena arena : arenas) {
            if(arena.isVacant()) {
                return arena;
            }
        }
        return null;
    }

    public static void setString(String path, String string) {
        file.set(path, string);
    }

    // TODO
    public static boolean paste(String schemName, int x, int y, int z) {

        final File file = new File(LuxDuels.getInstance().getDataFolder() + "/schematics/" + schemName + ".schematic");
        if(file.isFile()) {
            ClipboardReader reader;
            final ClipboardFormat format = ClipboardFormats.findByFile(file);
            BlockVector3 vec = BlockVector3.at(x,y,z);

            try {
                reader = format.getReader(new FileInputStream(file));
                Clipboard clipboard;
                clipboard = reader.read();
                clipboard.paste(FaweAPI.getWorld(world.getName()), vec, true);
                clipboard.relight(x,y,z);
                return true;
            } catch (IOException exception) {
                exception.printStackTrace();
                return false;
            }
        } else  {
            System.out.println("Error pasting schematic " + schemName);
            return false;
        }
    }

    public static Arena createAndGet(String schemName) {
        if(paste(schemName, last, 50, last)) {
            Location min = new Location(world, last + .5, 50, last - .5);
            Location max = new Location(world, last + 132.5, 200, last - 132.5);

            Arena arena = new Arena(min, max, schemName);
            saveArena(arena);
            addArena(arena);
            return arena;
        } else {
            return null;
        }

    }

    public static void loadArenas() {
        file.keySet().forEach(key -> {
            Arena arena = addArena(UUID.fromString(key), Cuboid.fromString(file.getString(key + ".Cuboid")), file.getString(key + ".Schematic"));
        });
    }

    public static void saveArena(Arena arena) {
        file.setPathPrefix(arena.getUUID().toString());
        file.set("Cuboid", arena.getCuboid().toString());
        file.set("Schematic", arena.getSchemName());
    }

    public static Arena addArena(UUID uuid, Cuboid cuboid, String schemName) {
        return new Arena(uuid, cuboid, schemName);
    }

    public static void addArena(Arena arena) {
        arenas.add(arena);
        last += 1000;
    }

    public static void removeArena(Arena arena) {
        arenas.remove(arena);
    }

    public static void removeArena(UUID uuid) {
        arenas.remove(getArenaByUUID(uuid));
    }

}
