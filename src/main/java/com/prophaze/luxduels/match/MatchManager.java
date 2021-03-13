package com.prophaze.luxduels.match;

import com.prophaze.luxduels.arena.Arena;
import com.prophaze.luxduels.kits.Kit;
import com.prophaze.luxduels.profile.Profile;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Zilleyy, ProPhaze
 * <br>
 * Date: 9/03/2021 @ 10:51 am AEST
 */
public class MatchManager {

    @Getter private static final List<Match> matches = new ArrayList<>();

    public static Match createDuelMatch(Arena arena, Kit kit, Profile profileOne) {
        Match match = new Match(arena, kit, profileOne);
        matches.add(match);
        return match;
    }

    public static Match createAndGet(Arena arena, Kit kit, Profile profileOne, Profile profileTwo) {
        Match result = new Match(arena, kit, profileOne, profileTwo);
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
