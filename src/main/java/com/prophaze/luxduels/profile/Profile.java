package com.prophaze.luxduels.profile;

import com.prophaze.luxduels.LuxDuels;
import com.prophaze.luxduels.file.Yaml;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 9:57 am AEST
 */
public class Profile {

    @Getter private final Yaml file;
    @Getter private final UUID UUID;
    @Getter private final Statistics playerStats;

    /**
     * Constructor for Profile, should only be called from the ProfileManager.
     * @param UUID
     */
    protected Profile(UUID UUID) {
        this.UUID = UUID;
        this.file = new Yaml(this.getUUID().toString(), LuxDuels.getInstance().getDataFolder().getAbsolutePath() + "/PlayerData/");
        this.playerStats = new Statistics();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.UUID);
    }

}
