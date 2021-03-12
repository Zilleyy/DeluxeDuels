package com.prophaze.luxduels.event;

import com.prophaze.luxduels.arena.ArenaManager;
import com.prophaze.luxduels.command.BuilderCommand;
import com.prophaze.luxduels.match.Match;
import com.prophaze.luxduels.match.MatchManager;
import com.prophaze.luxduels.profile.Profile;
import com.prophaze.luxduels.profile.ProfileManager;
import com.prophaze.luxduels.queue.Queue;
import com.prophaze.luxduels.util.item.Items;
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
import org.bukkit.event.player.PlayerInteractEvent;
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
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        player.getInventory().getItemInMainHand();
        if (player.getInventory().getItemInMainHand().isSimilar(Items.CASUAL)) {
            // open casual GUI selection
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Profile profile = ProfileManager.getProfile(event.getEntity().getUniqueId());
        if (MatchManager.isInMatch(profile)) {
            Match match = MatchManager.getMatch(profile);
            if (profile.getUUID().equals(match.getProfileOne().getUUID())) {
                match.setWinner(match.getProfileOne());
            }
            match.setWinner(match.getProfileTwo());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        send(player, "&4&lNOTE: &7This is a beta, please report all bugs in our discord.");
        ProfileManager.loadProfile(player.getUniqueId());
        Items.setServerItems(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Profile profile = ProfileManager.getProfile(event.getPlayer());
        Queue.removeProfile(profile);
        if (MatchManager.isInMatch(profile)) {
            Match match = MatchManager.getMatch(profile);
            if (match.getProfileOne().equals(profile)) {
                match.setWinner(match.getProfileTwo());
            } else match.setWinner(match.getProfileOne());
        }
        //TODO Make statistics work after base version release
        // profile.getFile().set(profile.getUUID().toString() + ".stats", profile.getPlayerStats().toString());
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Profile profile = ProfileManager.getProfile(event.getPlayer());
        event.setCancelled(true);
        if (MatchManager.isInMatch(profile)) {
            event.setCancelled(false);
            Match match = MatchManager.getMatch(profile);

            Block placed = event.getBlockPlaced();
            match.addBlock(Material.AIR, placed.getLocation());
        } else if (BuilderCommand.getBuilders().contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(false);
        }

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Profile profile = ProfileManager.getProfile(event.getPlayer().getUniqueId());

        event.setCancelled(true);
        if (MatchManager.isInMatch(profile)) {
            Match match = MatchManager.getMatch(profile);
            Block broken = event.getBlock();
            if (!match.containsBlockAt(broken.getLocation())) {
                event.setCancelled(true);
                send(profile, "&cYou cannot break blocks that were not placed by a player whilst in a match!");
                // ONLY PLAYER MADE BLOCKS CAN BE HAND BROKEN
            } else {
                event.setCancelled(false);
                match.removeBlockAt(broken.getLocation());
            }
        } else if (BuilderCommand.getBuilders().contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(false);
        }

    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        ArenaManager.getArenaContaining(event.getLocation()).getMatch().addBlocks(event.blockList().toArray(new Block[0]));
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Profile profile = ProfileManager.getProfile(event.getPlayer().getUniqueId());
        if (MatchManager.isInMatch(profile) || Queue.inQueue(profile)) {
            event.setCancelled(true);
        }
    }

}
