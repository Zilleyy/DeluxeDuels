package com.prophaze.luxduels.event;

import com.prophaze.luxduels.arena.ArenaManager;
import com.prophaze.luxduels.match.Match;
import com.prophaze.luxduels.match.MatchManager;
import com.prophaze.luxduels.profile.Profile;
import com.prophaze.luxduels.profile.ProfileManager;
import com.prophaze.luxduels.queue.Queue;
import com.prophaze.luxduels.util.Serialize;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import static com.prophaze.luxduels.util.Messenger.send;

/**
 * Author: Zilleyy, ProPhaze
 * <br>
 * Date: 9/03/2021 @ 11:04 am AEST
 */

public class PlayerListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Profile profile = ProfileManager.getProfile(event.getEntity().getUniqueId());
        // Temp testing code
        profile.getPlayerStats().setDeaths(profile.getPlayerStats().getDeaths() + 1);
        profile.getPlayerStats().setLosses(profile.getPlayerStats().getLosses() + 1);
        if(MatchManager.isInMatch(profile)) {
            Match match = MatchManager.getMatch(profile);
            if(profile.getUUID().equals(match.getProfileOne().getUUID())) {
                match.setWinner(match.getProfileOne());
            } match.setWinner(match.getProfileTwo());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        send(player, "&4&lNOTE: &7This is a beta, please report all bugs in our discord.");
        ProfileManager.loadProfile(player.getUniqueId());
        Profile profile = ProfileManager.getProfile(player);
        send(player, profile.getPlayerStats().getDeaths() + "");
        send(player, profile.getPlayerStats().getLosses() + "");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Profile profile = ProfileManager.getProfile(event.getPlayer());
        Queue.removeProfile(profile);
        if(MatchManager.isInMatch(profile)) {
            Match match = MatchManager.getMatch(profile);
            if(match.getProfileOne().equals(profile)) {
                match.setWinner(match.getProfileTwo());
            } else match.setWinner(match.getProfileOne());
        }
        profile.getFile().set(profile.getUUID().toString() + ".stats", profile.getPlayerStats().toString());
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Profile profile = ProfileManager.getProfile(event.getPlayer());
        if(MatchManager.isInMatch(profile)) {
            Match match = MatchManager.getMatch(profile);

            Block placed = event.getBlockPlaced();
            match.addBlock(Material.AIR, placed.getLocation());
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Profile profile = ProfileManager.getProfile(event.getPlayer().getUniqueId());
        if(MatchManager.isInMatch(profile)) {
            Match match = MatchManager.getMatch(profile);

            Block broken = event.getBlock();
            if(!match.containsBlockAt(broken.getLocation())) {
                event.setCancelled(true);
                send(profile, "&cYou cannot break blocks that were not placed by a player whilst in a match!");
                // ONLY PLAYER MADE BLOCKS CAN BE HAND BROKEN
            } else {
                match.removeBlockAt(broken.getLocation());
            }
        }

    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        ArenaManager.getArenaContaining(event.getLocation()).getMatch().addBlocks(event.blockList().toArray(new Block[0]));
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Profile profile = ProfileManager.getProfile(event.getPlayer().getUniqueId());
        if(MatchManager.isInMatch(profile) || Queue.inQueue(profile)) {
            event.setCancelled(true);
        }
    }

}
