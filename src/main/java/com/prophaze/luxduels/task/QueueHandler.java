package com.prophaze.luxduels.task;

import com.prophaze.luxduels.kits.Kit;
import com.prophaze.luxduels.kits.KitManager;
import com.prophaze.luxduels.match.Match;
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
        for(Kit kit : KitManager.serverKits) {
            if(Queue.hasNext(kit)) {
                Match match = Queue.next(kit, "ApOvergrownArena");

            }
        }
    }

}