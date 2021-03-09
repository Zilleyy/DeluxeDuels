package com.prophaze.luxduels.arena;

import com.prophaze.luxduels.match.Match;
import com.prophaze.luxduels.profile.Profile;
import com.prophaze.luxduels.util.Cuboid;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * Author: Zilleyy, ProPhaze
 * <br>
 * Date: 9/03/2021 @ 9:58 am AEST
 */
public class Arena {

    @Getter private String name;
    @Getter private Cuboid cuboid;

    @Getter @Setter private Match match;

    /**
     * Constructor for Arena, should only be called from the ArenaManager.
     * @param name
     * @param l1
     * @param l2
     */
    protected Arena(String name, Location l1, Location l2) {
        this.name = name;
        this.cuboid = new Cuboid(l1, l2);
    }

    public World getWorld() {
        return this.cuboid.getWorld();
    }

    public boolean isVacant() {
        return getMatch() == null;
    }

}
