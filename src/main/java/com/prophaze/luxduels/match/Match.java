package com.prophaze.luxduels.match;

import com.google.common.collect.Lists;
import com.prophaze.luxduels.arena.Arena;
import com.prophaze.luxduels.profile.Profile;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.*;
import java.util.AbstractMap.*;

/**
 * Author: Zilleyy, ProPhaze
 * <br>
 * Date: 9/03/2021 @ 10:42 am AEST
 */
public class Match {

    @Getter private final Arena arena;
    @Getter private final MatchSettings matchSettings;
    @Getter private final Profile profileOne, profileTwo;
    @Getter private MatchState matchState;

    private Profile winner;

    private List<UUID> matchSpectators;

    // Key = the material it was before it was modified
    private List<SimpleEntry<Material, Location>> blocks = new ArrayList<>();

    public Match(Profile profileOne, Profile profileTwo, Arena arena) {
        this.profileOne = profileOne;
        this.profileTwo = profileTwo;
        this.arena = arena;
        this.matchSettings = new MatchSettings();
        this.matchSpectators = Lists.newArrayList();
        this.matchState = MatchState.STARTING;
    }

    public boolean hasProfile(Profile profile) {
        return profile.getUUID().equals(this.profileOne.getUUID())
                || profile.getUUID().equals(this.profileTwo.getUUID());
    }

    public void setWinner(Profile winner) {
        if(!matchState.equals(MatchState.FINISHED)) setMatchState(MatchState.FINISHED);
        this.winner = winner;
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
        for(SimpleEntry<Material, Location> entry : this.blocks) {
            if(entry.getValue().equals(location)) this.blocks.remove(entry);
        }
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

    public Profile getWinner() {
        if(matchState.equals(MatchState.FINISHED)) return winner;
        return null;
    }

}

/**
 * This enum represents the possible states of the match.
 */
enum MatchState {
    WAITING, STARTING, PLAYING, FINISHED
}
