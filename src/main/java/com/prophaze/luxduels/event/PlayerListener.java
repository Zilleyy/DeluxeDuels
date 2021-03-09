package com.prophaze.luxduels.event;

import com.prophaze.luxduels.LuxDuels;
import com.prophaze.luxduels.profile.ProfileManager;
import org.bukkit.entity.Player;
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
        Player player = event.getPlayer();

        send(player, "&4&lNote: &7This is a beta, please report all bugs in our discord.");
        LuxDuels.getInstance().getProfileManager().loadProfile(player.getUniqueId());
    }

}
