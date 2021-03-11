package com.prophaze.luxduels.arena;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Zilleyy, ProPhaze
 * <br>
 * Date: 9/03/2021 @ 9:58 am AEST
 */
public class ArenaManager {

    private static List<Arena> arenas = new ArrayList<>();

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

    public static void loadArenas() {
        // TODO
    }

    public static void addArena(String name, Location l1, Location l2) {
        arenas.add(new Arena(name, l1, l2));
    }

    public static void removeArena(Arena arena) {
        arenas.remove(arena);
    }

    public static void removeArena(String name) {
        arenas.remove(getArenaByName(name));
    }

}
