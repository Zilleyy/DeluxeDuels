package com.prophaze.luxduels.match;

import com.prophaze.luxduels.arena.Arena;
import com.prophaze.luxduels.profile.Profile;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Zilleyy, ProPhaze
 * <br>
 * Date: 9/03/2021 @ 10:51 am AEST
 */
public class MatchManager {

    private static final List<Match> matches = new ArrayList<>();

    public static void createMatch(Profile profileOne, Profile profileTwo, Arena arena) {
        matches.add(new Match(profileOne, profileTwo, arena));
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
