package com.prophaze.luxduels.task;

import com.prophaze.luxduels.LuxDuels;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

import static com.prophaze.luxduels.util.Messenger.color;
import static com.prophaze.luxduels.util.Messenger.send;

/**
 * Author: Zilleyy
 * <br>
 * Date: 10/03/2021 @ 12:35 pm AEST
 */
public class Countdown extends BukkitRunnable {

    @Getter private String initial;
    @Getter private String format;
    @Getter private String complete;
    @Getter private int seconds;
    @Getter private final Player[] recipients;

    private AtomicBoolean start = new AtomicBoolean(true);

    /**
     * Make sure you run this runnable every 20 ticks (every second) when using it.
     * @param initial
     * @param format use {0} for the placeholder.
     * @param complete
     * @param seconds how many seconds to countdown from.
     * @param recipients the people to send the message to (message recipients).
     */
    public Countdown(String initial, String format, String complete, int seconds, Player... recipients) {
        this.initial = color(initial);
        this.format = color(format);
        this.complete = color(complete);
        this.seconds = seconds;
        this.recipients = recipients;

        this.runTaskTimerAsynchronously(LuxDuels.getInstance(), 20L, 20L);
    }

    @Override
    public void run() {
        if(start.get()) {
            for (Player player : this.recipients) { send(player, getInitialMessage()); }
            start.set(false);
            this.seconds--;
            return;
        }

        for (Player player : this.recipients) {
            send(player, getMessage());
        }
        this.seconds--;
        if(this.seconds <= 0) {
            for(Player player : this.recipients) {
                send(player, this.complete);
            }
            this.cancel();
        }
    }

    /**
     * @return the format with the placeholder replaced with the seconds remaining.
     */
    private String getMessage() {
        return this.format.replace("{0}", String.valueOf(this.seconds));
    }

    private String getInitialMessage() {
        return this.initial.replace("{0}", String.valueOf(this.seconds));
    }

}
