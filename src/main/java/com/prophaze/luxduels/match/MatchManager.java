package com.prophaze.luxduels.match;

import com.prophaze.luxduels.arena.Arena;
import com.prophaze.luxduels.profile.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 10:51 am AEST
 */
public class MatchManager {

    private final List<Match> matches = new ArrayList<>();

    public void createMatch(Profile profileOne, Profile profileTwo, Arena arena) {
        this.matches.add(new Match(profileOne, profileTwo, arena));
    }

    public Match getMatch(Profile profile) {
        return matches.stream().filter(match -> match.hasProfile(profile)).findFirst().orElse(null);
    }

    public int matchCount(Arena arena) {
        if(arena != null) {
            return (int) matches.stream().filter(match -> match.getArena().equals(arena)).count();
        }
        return matches.size();
    }

}
