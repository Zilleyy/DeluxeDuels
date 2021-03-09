package com.prophaze.luxduels.event;

import com.prophaze.luxduels.LuxDuels;
import com.prophaze.luxduels.profile.ProfileManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.prophaze.luxduels.util.Messenger.send;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 11:04 am AEST
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        send(event.getPlayer(), "LuxDuels is currently in development");
        LuxDuels.getInstance().getProfileManager().addProfile(event.getPlayer().getUniqueId());
    }

}
