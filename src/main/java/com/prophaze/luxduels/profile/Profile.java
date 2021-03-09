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
    @Getter private final UUID playerUUID;

    protected Profile(UUID playerUUID) {
        this.playerUUID = playerUUID;
        this.file = new Yaml(playerUUID.toString(), LuxDuels.getInstance().getDataFolder().getAbsolutePath() + "/PlayerData/");
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.playerUUID);
    }

}
