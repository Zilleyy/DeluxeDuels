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

    @Getter private final Cuboid cuboid;
    @Getter private final UUID UUID;
    @Getter private final String schemName;

    @Getter @Setter private Match match;
    @Getter @Setter private Location loc1, loc2;
    @Getter @Setter private boolean generatingMap = false;

    /**
     * Constructor for Arena, should only be called from the ArenaManager.
     * @param l1
     * @param l2
     */
    protected Arena(Location l1, Location l2, String schemName) {
        this.cuboid = new Cuboid(l1, l2);
        this.UUID = java.util.UUID.randomUUID();
        this.schemName = schemName;
    }

    protected Arena(UUID uuid, Cuboid cuboid, String schemName) {
        this.UUID = uuid;
        this.cuboid = cuboid;
        this.schemName = schemName;
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

    public boolean isComplete() {
        return loc1 != null && loc2 != null;
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
