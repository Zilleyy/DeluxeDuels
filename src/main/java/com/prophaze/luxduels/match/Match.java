package com.prophaze.luxduels.match;

import com.google.common.collect.Lists;
import com.prophaze.luxduels.arena.Arena;
import com.prophaze.luxduels.kits.Kit;
import com.prophaze.luxduels.profile.Profile;
import com.prophaze.luxduels.task.Countdown;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.*;
import java.util.AbstractMap.*;

import static com.prophaze.luxduels.util.Messenger.color;
import static com.prophaze.luxduels.util.Messenger.send;

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
    @Getter @Setter private Profile profileTwo;

    private Profile winner;

    private List<UUID> spectators;

    // Key = the material it was before it was modified
    private List<SimpleEntry<Material, Location>> blocks = new ArrayList<>();

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
        this.matchState = MatchState.WAITING;
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

    public void start() {
        this.matchState = MatchState.STARTING;
        new Countdown("&7Matching starting in &e{0} &7seconds...", "&e{0}&7...", "&7The game has started, GLHF!", 10, profileOne.getPlayer(), profileTwo.getPlayer());
        profileOne.getPlayer().teleport(arena.getLoc1());
        profileOne.getPlayer().teleport(arena.getLoc2());
    }

    public void end() {
        this.matchState = MatchState.FINISHED;

        String placeholder = this.matchState.replace(this.getWinner().getPlayer().getName());
        send(this.profileOne.getPlayer(), placeholder);
        send(this.profileTwo.getPlayer(), placeholder);

        resetBlocks();

        this.arena.setMatch(null);
    }

    /**
     * This enum represents the possible states of the match.
     */
    public enum MatchState {
        WAITING("Waiting to start the match..."),
        STARTING("Starting match in {0} seconds."),
        PLAYING("Match started, good luck!"),
        FINISHED("GG! {0} won the match.");

        @Getter private String message;

        MatchState(String message) {
            this.message = color(message);
        }

        public String replace(String var) {
            return this.message.replaceAll("[{0}]", var);
        }

    }

}
