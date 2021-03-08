package com.prophaze.luxduels.profile;

import com.prophaze.luxduels.LuxDuels;
import com.prophaze.luxduels.file.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 9:57 am AEST
 */
public class Profile {

    private Yaml file;

    private UUID uuid;

    protected Profile(UUID uuid) {
        this.uuid = uuid;
        this.file = new Yaml(uuid.toString(), LuxDuels.getInstance().getDataFolder().getAbsolutePath() + "/PlayerData/");
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }

}
