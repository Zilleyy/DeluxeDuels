package com.prophaze.luxduels.match;

import com.prophaze.luxduels.arena.Arena;
import com.prophaze.luxduels.profile.Profile;
import lombok.Getter;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 10:42 am AEST
 */
public class Match {

    @Getter private final Profile profileOne, profileTwo;
    @Getter private final Arena arena;

    public Match(Profile profileOne, Profile profileTwo, Arena arena) {
        this.profileOne = profileOne;
        this.profileTwo = profileTwo;
        this.arena = arena;
    }

}
