package com.prophaze.luxduels.task;

import com.prophaze.luxduels.LuxDuels;
import com.prophaze.luxduels.kits.Kit;
import com.prophaze.luxduels.kits.KitManager;
import com.prophaze.luxduels.match.Match;
import com.prophaze.luxduels.queue.Queue;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import static com.prophaze.luxduels.util.Messenger.send;

/**
 * Author: Zilleyy
 * <br>
 * Date: 10/03/2021 @ 12:59 pm AEST
 */
public class QueueHandler extends BukkitRunnable {

    @Override
    public void run() {
        for (Kit kit : KitManager.serverKits) {
            if (Queue.hasNext(kit)) {
                Match match = Queue.next(kit);
                if (match == null) {
                    System.out.println("Match is somehow null wtf");
                } else {
                    if (match.getArena().isGeneratingMap()) {
                        send(match.getProfileOne(), "&ePlease give the Arena one moment to prepare itself.");
                        send(match.getProfileTwo(), "&ePlease give the Arena one moment to prepare itself.");
                        match.getArena().setLoc1(match.getArena().getCuboid().getCenter());
                        match.getArena().setLoc2(match.getArena().getCuboid().getCenter());
                        Bukkit.getScheduler().runTaskLater(LuxDuels.getInstance(), match::start, 20 * 4);
                    } else {
                        match.start();
                    }
                }

            }
        }
    }

}