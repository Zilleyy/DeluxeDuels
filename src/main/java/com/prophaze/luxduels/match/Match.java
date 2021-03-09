package com.prophaze.luxduels.match;

import com.google.common.collect.Lists;
import com.prophaze.luxduels.arena.Arena;
import com.prophaze.luxduels.profile.Profile;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.*;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 10:42 am AEST
 */
public class Match {

    @Getter private final Arena arena;
    @Getter private final MatchSettings matchSettings;
    @Getter @Setter private Profile profileOne, profileTwo;
    @Getter @Setter private MatchState matchState;

    private List<UUID> matchSpectators;

    // Key = the material it was before it was modified
    private Map<Material, List<Location>> blocks = new HashMap<>();

    public Match(Profile profileOne, Profile profileTwo, Arena arena) {
        this.profileOne = profileOne;
        this.profileTwo = profileTwo;
        this.arena = arena;
        this.matchSettings = new MatchSettings();
        this.matchSpectators = Lists.newArrayList();
        this.setState(MatchState.STARTING);
    }

    public boolean hasProfile(Profile profile) {
        return profile.getUUID().equals(this.profileOne.getUUID())
                || profile.getUUID().equals(this.profileTwo.getUUID());
    }

    public MatchState getState() {
        return this.matchState;
    }

    public void setState(MatchState newState) {
        this.matchState = newState;
    }

    public void addBlock(Material key, Location value) {
        if(this.blocks.containsKey(key)) {
            List<Location> locations = this.blocks.get(key);
            locations.add(value);
            this.blocks.put(key, locations);
        } else {
            this.blocks.put(key, Collections.singletonList(value));
        }
    }

    public void addBlocks(Material key, Location... values) {
        if(this.blocks.containsKey(key)) {
            List<Location> locations = this.blocks.get(key);
            for(Location location : values) {
                locations.add(location);
            }
            this.blocks.put(key, locations);
        } else {
            this.blocks.put(key, Arrays.asList(values));
        }
    }

}

/**
 * This enum represents the possible states of the match.
 */
enum MatchState {
    WAITING, STARTING, PLAYING, FINISHED
}
