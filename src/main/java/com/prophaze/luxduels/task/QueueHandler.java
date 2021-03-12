package com.prophaze.luxduels.task;

import com.prophaze.luxduels.match.Match;
import com.prophaze.luxduels.match.MatchType;
import com.prophaze.luxduels.queue.Queue;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Author: Zilleyy
 * <br>
 * Date: 10/03/2021 @ 12:59 pm AEST
 */
public class QueueHandler extends BukkitRunnable {

    @Override
    public void run() {
        for(MatchType type : MatchType.values()) {
            if(Queue.hasNext(type)) {
                Match match = Queue.next(type);
                match.getProfileOne().getPlayer().teleport(match.getArena().getSpawnPos()[0]);
                match.getProfileTwo().getPlayer().teleport(match.getArena().getSpawnPos()[1]);
            }
        }
    }

}
