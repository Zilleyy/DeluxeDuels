package com.prophaze.luxduels.match;

import com.prophaze.luxduels.arena.Arena;
import com.prophaze.luxduels.profile.Profile;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 10:42 am AEST
 */
public class Match {
    
    @Getter private final Arena arena;
    @Getter private final MatchSettings matchSettings;

    @Getter @Setter private Profile profileOne, profileTwo;

    public Match(Profile profileOne, Profile profileTwo, Arena arena) {
        this.profileOne = profileOne;
        this.profileTwo = profileTwo;
        this.arena = arena;
        this.matchSettings = new MatchSettings();
    }

    public boolean hasProfile(Profile profile) {
        return profile.getPlayerUUID().equals(profileOne.getPlayerUUID())
                || profile.getPlayerUUID().equals(profileTwo.getPlayerUUID());
    }

}
