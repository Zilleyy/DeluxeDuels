package com.prophaze.luxduels.profile;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 10:33 am AEST
 */
public class Statistics {

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
        // Serialize.deserializeEncodedBukkitObject(profile.getFile().getString(profile.getUUID().toString() + ".stats"));
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
        // return Serialize.encodeBukkitObject(this);
        return "";
    }


}
