package com.prophaze.luxduels.match;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.prophaze.luxduels.LuxDuels;
import com.prophaze.luxduels.arena.Arena;
import com.prophaze.luxduels.event.PlayerListener;
import com.prophaze.luxduels.kits.Kit;
import com.prophaze.luxduels.profile.Profile;
import com.prophaze.luxduels.task.CountdownTimer;
import com.prophaze.luxduels.util.Constants;
import com.prophaze.luxduels.util.Messenger;
import com.prophaze.luxduels.util.item.Items;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.AbstractMap.*;

import static com.prophaze.luxduels.util.Messenger.*;

/**
 * Author: Zilleyy, ProPhaze
 * <br>
 * Date: 9/03/2021 @ 10:42 am AEST
 */
public class Match {

    @Getter private final Arena arena;
    @Getter private final MatchSettings matchSettings;
    @Getter private final Profile profileOne;
    @Getter private final Kit kit;
    @Getter private MatchState matchState;
    @Getter private final List<CountdownTimer> countdowns = Lists.newArrayList();

    @Getter @Setter private Profile profileTwo;

    private Profile winner;

    private final List<UUID> spectators;

    // Key = the material it was before it was modified
    private final List<SimpleEntry<Material, Location>> blocks = Lists.newArrayList();

    protected Match(Arena arena, Kit kit, Profile profileOne) {
        this.arena = arena;
        this.kit = kit;
        this.profileOne = profileOne;

        this.matchSettings = new MatchSettings();
        this.spectators = Lists.newArrayList();
        this.matchState = MatchState.WAITING;
    }

    protected Match(Arena arena, Kit kit, Profile profileOne, Profile profileTwo) {
        this.arena = arena;
        this.kit = kit;
        this.profileOne = profileOne;
        this.profileTwo = profileTwo;

        this.matchSettings = new MatchSettings();
        this.spectators = Lists.newArrayList();
    }

    public void addSpectator(Profile profile) {
        this.spectators.add(profile.getUUID());
    }

    public void addSpectator(UUID uuid) {
        this.spectators.add(uuid);
    }

    public void setWinner(Profile winner) {
        this.winner = winner;
        end();
    }

    public void setMatchState(MatchState matchState) {
        this.matchState = matchState;
    }

    public void addBlocks(Block... values) {
        for(Block block : values) {
            this.blocks.add(new SimpleEntry<>(block.getType(), block.getLocation()));
        }
    }

    public void addBlock(Material key, Location value) {
        this.blocks.add(new SimpleEntry<>(key, value));
    }

    public void removeBlockAt(Location location) {
        this.blocks.removeIf(entry -> entry.getValue().equals(location));
    }

    /**
     * Sets all the blocks back to what they were and then clear the block list.
     */
    public void resetBlocks() {
        for(SimpleEntry<Material, Location> entry : this.blocks) {
            entry.getValue().getBlock().setType(entry.getKey());
        }
        this.blocks.clear();
    }

    public boolean containsBlock(Material key, Location value) {
        return blocks.contains(new SimpleEntry<>(key,value));
    }

    public boolean containsBlockAt(Location location) {
        for(SimpleEntry<Material, Location> entry : this.blocks) {
            if(entry.getValue().equals(location)) return true;
        }
        return false;
    }

    public boolean hasProfile(Profile profile) {
        return profile.getUUID().equals(this.profileOne.getUUID()) || profile.getUUID().equals(this.profileTwo.getUUID());
    }

    public Profile getWinner() {
        if(matchState.equals(MatchState.FINISHED)) return this.winner;
        return null;
    }

    public boolean canStart() {
        return profileOne != null && profileTwo != null;
    }

    public void start() {
        if(canStart()) {
            this.matchState = MatchState.STARTING;
            CountdownTimer timer = new CountdownTimer(LuxDuels.getInstance(),
                    6,
                    () -> {
                // Locations need to be setup beforehand
                        profileOne.getPlayer().teleport(getArena().getLoc1());
                        profileTwo.getPlayer().teleport(getArena().getLoc2());
                        PlayerListener.cantMove.add(profileOne);
                        PlayerListener.cantMove.add(profileTwo);
                        kit.apply(profileOne.getPlayer());
                        kit.apply(profileTwo.getPlayer());
                    },
                    () -> {
                        PlayerListener.cantMove.remove(profileOne);
                        PlayerListener.cantMove.remove(profileTwo);
                        this.matchState = MatchState.PLAYING;
                    },
                    (t) -> {
                        sendTitle(profileOne.getPlayer(), t.getSecondsLeft() + "","");
                        sendTitle(profileTwo.getPlayer(), t.getSecondsLeft() + "","");
                    }
            );
            timer.scheduleTimer();
            countdowns.add(timer);
            Bukkit.getScheduler().runTaskLater(LuxDuels.getInstance(), () -> countdowns.remove(timer), 20 * 6);
        }
    }

    public void end() {
        this.matchState = MatchState.FINISHED;
        NumberFormat formatter = new DecimalFormat("#0.0");
        String placeholder = Messenger.color("&bWinner: &a%name% &7(&c❤ %hearts_left%&7)"
                .replace("%name%", winner.getPlayer().getName())
                .replace("%hearts_left%", formatter.format(winner.getPlayer().getHealth() / 2)));
        send(this.profileOne.getPlayer(), placeholder);
        send(this.profileTwo.getPlayer(), placeholder);

        resetBlocks();

        Bukkit.getScheduler().runTaskLater(LuxDuels.getInstance(), () -> {
            if(profileOne.getPlayer() != null) profileOne.getPlayer().teleport(Constants.spawnLocation);
            if(profileTwo.getPlayer() != null) profileTwo.getPlayer().teleport(Constants.spawnLocation);
            profileOne.getPlayer().setHealth(20.0);
            profileTwo.getPlayer().setHealth(20.0);

            Items.setServerItems(profileOne.getPlayer());
            Items.setServerItems(profileTwo.getPlayer());

            this.arena.setMatch(null);
            MatchManager.getMatches().remove(this);
        }, 2L);


    }

    /**
     * This enum represents the possible states of the match.
     */
    public enum MatchState {
        WAITING(),
        STARTING(),
        PLAYING(),
        FINISHED()

    }

}
