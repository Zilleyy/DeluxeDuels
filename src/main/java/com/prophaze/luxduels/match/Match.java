package com.prophaze.luxduels.match;

import com.google.common.collect.Lists;
import com.prophaze.luxduels.arena.Arena;
import com.prophaze.luxduels.profile.Profile;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 10:42 am AEST
 */
public class Match {

    @Getter private final Arena arena;
    @Getter private final MatchSettings matchSettings;
    @Getter @Setter private Profile profileOne, profileTwo;
    @Getter @Setter private MatchState matchState;

    private List<UUID> matchSpectators;

    public Match(Profile profileOne, Profile profileTwo, Arena arena) {
        this.profileOne = profileOne;
        this.profileTwo = profileTwo;
        this.arena = arena;
        this.matchSettings = new MatchSettings();
        this.matchSpectators = Lists.newArrayList();
        this.setState(MatchState.STARTING);
    }

    public boolean hasProfile(Profile profile) {
        return profile.getUUID().equals(this.profileOne.getUUID())
                || profile.getUUID().equals(this.profileTwo.getUUID());
    }

    public MatchState getState() {
        return this.matchState;
    }

    public void setState(MatchState newState) {
        this.matchState = newState;
    }

}

/**
 * This enum represents the possible states of the match.
 */
enum MatchState {
    WAITING, STARTING, PLAYING, FINISHED
}
