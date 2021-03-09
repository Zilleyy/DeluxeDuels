package com.prophaze.luxduels.event;

import com.prophaze.luxduels.LuxDuels;
import com.prophaze.luxduels.match.Match;
import com.prophaze.luxduels.profile.Profile;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.prophaze.luxduels.util.Messenger.send;

/**
 * Author: Zilleyy, ProPhaze
 * <br>
 * Date: 9/03/2021 @ 11:04 am AEST
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Profile profile = LuxDuels.getInstance().getProfileManager().getProfileByUUID(event.getEntity().getUniqueId());
        if(LuxDuels.getInstance().getMatchManager().isInMatch(profile)) {
            Match match = LuxDuels.getInstance().getMatchManager().getMatch(profile);
            if(profile.getUUID().equals(match.getProfileOne().getUUID())) {
                match.setWinner(match.getProfileOne());
            } match.setWinner(match.getProfileTwo());
        }
    }

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

            Block placed = event.getBlockPlaced();
            match.addBlock(Material.AIR, placed.getLocation());
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Profile profile = LuxDuels.getInstance().getProfileManager().getProfileByUUID(event.getPlayer().getUniqueId());
        if(LuxDuels.getInstance().getMatchManager().isInMatch(profile)) {
            Match match = LuxDuels.getInstance().getMatchManager().getMatch(profile);

            Block broken = event.getBlock();
            if(!match.containsBlockAt(broken.getLocation())) {
                event.setCancelled(true);
                // ONLY PLAYER MADE BLOCKS CAN BE HAND BROKEN
            } else {
                match.removeBlockAt(broken.getLocation());
            }
        }

    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        LuxDuels.getInstance().getArenaManager().getArenaContaining(event.getLocation()).getMatch().addBlocks(event.blockList().toArray(new Block[0]));
    }

}
