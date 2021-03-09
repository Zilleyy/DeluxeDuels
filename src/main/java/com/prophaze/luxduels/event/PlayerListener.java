package com.prophaze.luxduels.event;

import com.prophaze.luxduels.LuxDuels;
import com.prophaze.luxduels.match.Match;
import com.prophaze.luxduels.match.MatchManager;
import com.prophaze.luxduels.profile.Profile;
import com.prophaze.luxduels.profile.ProfileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
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

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Profile profile = LuxDuels.getInstance().getProfileManager().getProfileByUUID(event.getPlayer().getUniqueId());
        if(LuxDuels.getInstance().getMatchManager().isInMatch(profile)) {
            Match match = LuxDuels.getInstance().getMatchManager().getMatch(profile);
            match.addPlacedBlock(event.getBlockPlaced());
        }
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {

    }

}
