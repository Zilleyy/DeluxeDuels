package com.prophaze.luxduels.arena;

import com.prophaze.luxduels.match.Match;
import com.prophaze.luxduels.util.Cuboid;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.UUID;

/**
 * Author: Zilleyy, ProPhaze
 * <br>
 * Date: 9/03/2021 @ 9:58 am AEST
 */
public class Arena {

    @Getter private String name;
    @Getter private Cuboid cuboid;
    @Getter private UUID UUID;

    @Getter @Setter private Match match;
    @Getter @Setter private Location loc1, loc2;

    /**
     * Constructor for Arena, should only be called from the ArenaManager.
     * @param l1
     * @param l2
     */
    protected Arena(Location l1, Location l2) {
        this.cuboid = new Cuboid(l1, l2);
        this.UUID = java.util.UUID.randomUUID();
    }

    protected Arena(UUID uuid, Cuboid cuboid) {
        this.UUID = uuid;
        this.cuboid = cuboid;
    }

    public void setSpawnPos(Location loc, int profileNumber) {
        switch (profileNumber) {
            case 1:
                loc1 = loc;
                break;
            case 2:
                loc2 = loc;
                break;
        }
    }

    public void saveArena() {

    }

    public World getWorld() {
        return Bukkit.getWorld(this.cuboid.getWorldName());
    }

    public boolean isVacant() {
        return getMatch() == null;
    }

    public Location[] getSpawnPos() {
        return new Location[] {
                loc1,
                loc2
        };
    }

    @Override
    public String toString() {
        return getUUID().toString() + "," + cuboid.toString();
    }

}
