package com.prophaze.luxduels.task;

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
                Queue.next(type);
            }
        }
    }

}
