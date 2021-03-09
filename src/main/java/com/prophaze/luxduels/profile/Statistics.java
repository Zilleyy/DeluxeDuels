package com.prophaze.luxduels.profile;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 10:33 am AEST
 */
public class Statistics {

    @Getter @Setter private int kills = 0,deaths = 0,wins = 0,losses = 0,elo = 0,totalMatches = 0;

    public Statistics(Profile profile) {
        profile.getFile().set("players." + profile.getUUID() + ".stats", this.toString());
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

    @Override
    public String toString() {
        return kills + "," + deaths + ","+ wins + "," + losses + "," + elo + "," + totalMatches;
    }
}
