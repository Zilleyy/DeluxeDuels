package com.prophaze.luxduels.match;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 10:51 am AEST
 */
public class MatchManager {

    @Getter private final List<Match> matches = new ArrayList<>();

    public void createMatch() {
        this.matches.add(new Match(null, null, null));
    }

}
