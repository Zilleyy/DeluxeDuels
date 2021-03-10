package com.prophaze.luxduels.task;

import com.prophaze.luxduels.match.Match;
import com.prophaze.luxduels.match.MatchManager;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Author: Zilleyy
 * <br>
 * Date: 10/03/2021 @ 1:02 pm AEST
 */
public class MatchHandler extends BukkitRunnable {

    @Override
    public void run() {
        for (Match match : MatchManager.getMatches()) {
            if(match.getMatchState() != Match.MatchState.WAITING) return;
            match.start();
        }
    }

}
