package com.prophaze.luxduels.arena;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 9:58 am AEST
 */
public class ArenaManager {

    private List<Arena> arenas = new ArrayList<>();

    /**
     * @param name The name used to identify the arena.
     * @return either the arena matching the name, or null if nothing matches.
     */
    public Arena getArenaByName(String name) {
        for(Arena arena : this.arenas) {
            if(arena.getName().equals(name)) return arena;
        }
        return null;
    }

    public void addArena(String name, Location location) {
        this.arenas.add(new Arena(name, location));
    }

    public void removeArena(Arena arena) {
        this.arenas.remove(arena);
    }

    public void removeArena(String name) {
        this.arenas.remove(getArenaByName(name));
    }

}
