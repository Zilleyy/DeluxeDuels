package com.prophaze.luxduels.match;

import com.prophaze.luxduels.arena.Arena;
import com.prophaze.luxduels.profile.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Zilleyy, ProPhaze
 * <br>
 * Date: 9/03/2021 @ 10:51 am AEST
 */
public class MatchManager {

    private static final List<Match> matches = new ArrayList<>();

    public static void create(Arena arena, MatchType type, Profile profileOne, Profile profileTwo) {
        matches.add(new Match(arena, type, profileOne, profileTwo));
    }

    public static Match createAndGet(Arena arena, MatchType type, Profile profileOne, Profile profileTwo) {
        Match result = new Match(arena, type, profileOne, profileTwo);
        matches.add(result);
        return result;
    }

    public static Match getMatch(Profile profile) {
        return matches.stream().filter(match -> match.hasProfile(profile)).findFirst().orElse(null);
    }

    public static boolean isInMatch(Profile profile) {
        for(Match match : matches) {
            if(match.hasProfile(profile)) return true;
        }
        return false;
    }

    public static int matchCount(Arena arena) {
        if(arena != null) {
            return (int) matches.stream().filter(match -> match.getArena().equals(arena)).count();
        }
        return matches.size();
    }

}
