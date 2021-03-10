package com.prophaze.luxduels.task;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

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
    @Getter private int seconds;
    @Getter private final Player[] recipients;

    private boolean first = true;

    /**
     * Make sure you run this runnable every 20 ticks (every second) when using it.
     * @param format use {0} for the placeholder.
     * @param seconds how many seconds to countdown from.
     * @param recipients the people to send the message to (message recipients).
     */
    public Countdown(String initial, String format, int seconds, Player... recipients) {
        this.initial = color(initial);
        this.format = color(format);
        this.seconds = seconds;
        this.recipients = recipients;
    }

    @Override
    public void run() {
        if(first) {
            for (Player player : this.recipients) { send(player, getInitialMessage()); }
            return;
        }

        for (Player player : this.recipients) {
            send(player, getMessage());
        }
        this.seconds--;
        if(this.seconds <= 0) this.cancel();
    }

    /**
     * @return the format with the placeholder replaced with the seconds remaining.
     */
    private String getMessage() {
        return this.format.replaceAll("[{0}]", String.valueOf(this.seconds));
    }

    private String getInitialMessage() {
        return this.initial.replaceAll("[{0}]", String.valueOf(this.seconds));
    }

}
