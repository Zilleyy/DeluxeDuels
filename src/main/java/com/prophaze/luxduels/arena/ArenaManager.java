package com.prophaze.luxduels.arena;

import com.prophaze.luxduels.LuxDuels;
import com.prophaze.luxduels.file.Yaml;
import com.prophaze.luxduels.util.Cuboid;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Zilleyy, ProPhaze
 * <br>
 * Date: 9/03/2021 @ 9:58 am AEST
 */
public class ArenaManager {

    private static final Yaml file = LuxDuels.getInstance().getArenaFile();

    private static final List<Arena> arenas = new ArrayList<>();

    private static final World world = Bukkit.getWorld("arenas");

    /**
     * @param name The name used to identify the arena.
     * @return either the arena matching the name, or null if nothing matches.
     */
    public static Arena getArenaByName(String name) {
        for(Arena arena : arenas) {
            if(arena.getName().equals(name)) return arena;
        }
        return null;
    }

    public static Arena getArenaContaining(Location location) {
        for(Arena arena : arenas) {
            if(arena.getCuboid().contains(location)) return arena;
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

    public static BlockVector3[] paste(String schematic, int x, int y, int z) {
        BlockVector3[] minMax = new BlockVector3[2];

        final File file = new File(LuxDuels.getInstance().getDataFolder() + "/schematics/" + schematic + ".schematic");

        final Clipboard clipboard;
        final ClipboardFormat format = ClipboardFormats.findByFile(file);
        try (final ClipboardReader reader = format.getReader(new FileInputStream(file))) {
            clipboard = reader.read();
            minMax[0] = clipboard.getMinimumPoint();
            minMax[1] = clipboard.getMaximumPoint();
            try (final EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(world), -1)) {
                final Operation operation = new ClipboardHolder(clipboard)
                        .createPaste(editSession)
                        .to(BlockVector3.at(x, y, z))
                        .build();
                Operations.complete(operation);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return minMax;
    }

    public static void createArena(String name, String schematic, int x, int y, int z) {
        BlockVector3[] minMax = paste(schematic, x, y, z);
        Location min = new Location(world, minMax[0].getBlockX(), minMax[0].getBlockY(), minMax[0].getBlockZ());
        Location max = new Location(world, minMax[1].getBlockX(), minMax[1].getBlockY(), minMax[1].getBlockZ());

        Arena arena = new Arena(name, min, max);
        saveArena(arena);
        arenas.add(arena);
    }

    public static void loadArenas() {
        for(String name : file.keySet()) {
            Cuboid cuboid = file.getSerializable("Cuboid", Cuboid.class);
            addArena(name, cuboid);
        }
    }

    public static void saveArena(Arena arena) {
        file.setPathPrefix(arena.getName());
        file.set("Cuboid", arena.getCuboid());
    }

    public static void addArena(String name, Cuboid cuboid) {
        arenas.add(new Arena(name, cuboid));
    }

    public static void removeArena(Arena arena) {
        arenas.remove(arena);
    }

    public static void removeArena(String name) {
        arenas.remove(getArenaByName(name));
    }

}
