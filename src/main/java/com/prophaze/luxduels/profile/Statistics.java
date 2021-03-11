package com.prophaze.luxduels.profile;

import com.prophaze.luxduels.util.Serialize;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 10:33 am AEST
 */
public class Statistics implements ConfigurationSerializable {

    @Getter @Setter private int kills,deaths,wins,losses,elo,totalMatches;

    // Default Constructor for when file is empty.
    public Statistics() {
        this.kills = 0;
        this.deaths = 0;
        this.wins = 0;
        this.losses = 0;
        this.elo = 0;
        this.totalMatches = 0;
    }

    // Constructor whenever they have statistics to load.
    public Statistics(Profile profile) {
        Serialize.deserializeEncodedBukkitObject(profile.getFile().getString(profile.getUUID().toString() + ".stats"));
    }

    // Could name each variable in the constructor but im lazy
    private Statistics(int... stats) {
        this.kills = stats[0];
        this.deaths = stats[1];
        this.wins = stats[2];
        this.losses = stats[3];
        this.elo = stats[4];
        this.totalMatches = stats[5];
    }

    /**
     * Encodes for storage to player data file.
     * @return Base64 encoded.
     */
    @Override
    public String toString() {
        return Serialize.encodeBukkitObject(this);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<>();

        data.put("Kills", this.kills);
        data.put("Deaths", this.deaths);
        data.put("Wins", this.wins);
        data.put("Losses", this.losses);
        data.put("ELO", this.elo);
        data.put("TotalMatches", this.totalMatches);
        return data;
    }

    public Statistics valueOf(Map<String, Object> data) {
        return deserialize(data);
    }

    public static Statistics deserialize(Map<String, Object> data) {
        int kills = (int) data.get("Kills");
        int deaths = (int) data.get("Deaths");
        int wins = (int) data.get("Wins");
        int losses = (int) data.get("Losses");
        int elo = (int) data.get("ELO");
        int totalMatches = (int) data.get("TotalMatches");

        return new Statistics(kills, deaths, wins, losses, elo, totalMatches);
    }

}
