package com.prophaze.luxduels;

import com.prophaze.luxduels.arena.ArenaManager;
import com.prophaze.luxduels.match.MatchManager;
import com.prophaze.luxduels.profile.ProfileManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class LuxDuels extends JavaPlugin {

    @Getter public static LuxDuels instance;

    @Getter private ProfileManager profileManager;
    @Getter private ArenaManager arenaManager;
    @Getter private MatchManager matchManager;

    @Override
    public void onEnable() {
        instance = this;

        this.loadManagers();
    }

    private void registerEvents() {

    }

    private void loadManagers() {
        this.profileManager = new ProfileManager();
        this.arenaManager = new ArenaManager();
        this.matchManager = new MatchManager();
    }

}
