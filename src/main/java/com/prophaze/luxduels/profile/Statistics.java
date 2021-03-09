package com.prophaze.luxduels.profile;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 10:33 am AEST
 */
public class Statistics {

    private final Profile profile;
    @Getter @Setter public int kills,deaths,wins,losses,elo,totalMatches;

    public Statistics(Profile profile) {
        this.profile = profile;
    }
}
