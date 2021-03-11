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

    @Getter @Setter private int kills = 0,deaths = 0,wins = 0,losses = 0,elo = 0,totalMatches = 0;

    public Statistics(Profile profile) {
        profile.getFile().set("players." + profile.getUUID() + ".stats", this.toString());
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

    private Statistics(String[] stats) {
        setKills(Integer.parseInt(stats[0]));
        setDeaths(Integer.parseInt(stats[1]));
        setWins(Integer.parseInt(stats[2]));
        setLosses(Integer.parseInt(stats[3]));
        setElo(Integer.parseInt(stats[4]));
        setTotalMatches(Integer.parseInt(stats[5]));
    }

    public static Statistics fromString(String deserialize) {
        return new Statistics(deserialize.split(","));
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
        data.put("Wins", this.deaths);
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
