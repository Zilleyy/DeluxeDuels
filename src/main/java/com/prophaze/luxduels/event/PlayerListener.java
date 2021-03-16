package com.prophaze.luxduels.event;

import com.google.common.collect.Lists;
import com.prophaze.luxduels.LuxDuels;
import com.prophaze.luxduels.arena.Arena;
import com.prophaze.luxduels.arena.ArenaManager;
import com.prophaze.luxduels.command.BuilderCommand;
import com.prophaze.luxduels.inventory.Inventories;
import com.prophaze.luxduels.match.Match;
import com.prophaze.luxduels.match.MatchManager;
import com.prophaze.luxduels.profile.Profile;
import com.prophaze.luxduels.profile.ProfileManager;
import com.prophaze.luxduels.queue.Queue;
import com.prophaze.luxduels.task.CountdownTimer;
import com.prophaze.luxduels.util.Constants;
import com.prophaze.luxduels.util.item.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

import java.util.List;

import static com.prophaze.luxduels.util.Messenger.send;

/**
 * Author: Zilleyy, ProPhaze
 * <br>
 * Date: 9/03/2021 @ 11:04 am AEST
 */

public class PlayerListener implements Listener {

    public static List<Profile> cantMove = Lists.newArrayList();

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (event.getFrom().getZ() != event.getTo().getZ() && event.getFrom().getX() != event.getTo().getX()) {
            if(cantMove.contains(ProfileManager.getProfile(player))) event.setCancelled(true);
        }
    }

    //TODO Turn into actual listener instead of debug method for kits.
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Profile player = ProfileManager.getProfile(event.getPlayer());
        player.getPlayer().getInventory().getItemInMainHand();
        if (player.getPlayer().getInventory().getItemInMainHand().isSimilar(Items.CASUAL)) {
            // Open GUI to pick Kit
            Inventories.MatchTypeInventory.open(player.getPlayer());
        } else if (player.getPlayer().getInventory().getItemInMainHand().isSimilar(Items.COMP)) {
            //TODO Develop when enabled after beta
        } else if (player.getPlayer().getInventory().getItemInMainHand().isSimilar(Items.PARTY)) {
            //TODO Develop when enabled after beta
        } else if (player.getPlayer().getInventory().getItemInMainHand().isSimilar(Items.EDITOR)) {
            //TODO Develop when enabled after beta
        }else if (player.getPlayer().getInventory().getItemInMainHand().isSimilar(Items.SPECTATOR)) {
            // Open spectator GUI
        } else if (player.getPlayer().getInventory().getItemInMainHand().isSimilar(Items.LEAVE_QUEUE)) {
            Queue.removeProfile(player);
            Items.setServerItems(player.getPlayer());
            send(player, "&cYou have left the matchmaking Queue");
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.getDrops().clear();
        event.setDroppedExp(0);
        Profile profile = ProfileManager.getProfile(event.getEntity().getUniqueId());
        if (MatchManager.isInMatch(profile)) {
            Match match = MatchManager.getMatch(profile);
            if (profile.getUUID().equals(match.getProfileOne().getUUID())) {
                match.setWinner(match.getProfileTwo());
            } else match.setWinner(match.getProfileOne());
        }

        Bukkit.getScheduler().runTaskLater(LuxDuels.getInstance(), () -> {
            event.getEntity().spigot().respawn();
            if(!BuilderCommand.getBuilders().contains(profile.getUUID())) {
                Items.setServerItems(profile.getPlayer());
            }
        }, 3L);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        send(player, "&4&lNOTE: &7This is a beta, please report all bugs in our discord.");
        ProfileManager.loadProfile(player.getUniqueId());
        if(!BuilderCommand.getBuilders().contains(player.getUniqueId())) Items.setServerItems(player);
        Bukkit.getScheduler().runTaskLater(LuxDuels.getInstance(), () -> player.teleport(Constants.spawnLocation), 2L);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Profile profile = ProfileManager.getProfile(event.getPlayer());
        Queue.removeProfile(profile);
        if (MatchManager.isInMatch(profile)) {
            Match match = MatchManager.getMatch(profile);
            if (match.getProfileOne().equals(profile)) {
                match.setWinner(match.getProfileTwo());
            } else {
                match.setWinner(match.getProfileOne());
            }
            if(match.getCountdowns().size() > 0) match.getCountdowns().forEach(CountdownTimer::stopTask);
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
        event.setCancelled(true);
        if(BuilderCommand.getBuilders().contains(profile.getUUID())) {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        event.setResult(Event.Result.DENY);
        Player player = (Player) event.getWhoClicked();
        if(BuilderCommand.getBuilders().contains(player.getUniqueId()) || MatchManager.isInMatch(ProfileManager.getProfile(player))) {
            event.setResult(Event.Result.ALLOW);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            event.setCancelled(true);
            Profile profile = ProfileManager.getProfile(player);
            if(BuilderCommand.getBuilders().contains(player.getUniqueId()) || (MatchManager.isInMatch(profile) && MatchManager.getMatch(profile).getMatchState().equals(Match.MatchState.PLAYING)))
                event.setCancelled(false);
         }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        Player player = (Player)event.getEntity();
        event.setCancelled(true);
        if(BuilderCommand.getBuilders().contains(player.getUniqueId()) || MatchManager.isInMatch(ProfileManager.getProfile(player)))
            event.setCancelled(false);
    }

}
