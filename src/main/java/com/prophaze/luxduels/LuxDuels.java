package com.prophaze.luxduels;

import com.prophaze.luxduels.arena.ArenaManager;
import com.prophaze.luxduels.profile.ProfileManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LuxDuels extends JavaPlugin {

    private static LuxDuels instance;

    private ProfileManager profileManager;
    private ArenaManager arenaManager;

    @Override
    public void onEnable() {
        instance = this;

        this.loadManagers();
    }

    public static LuxDuels getInstance() {
        return instance;
    }

    public ProfileManager getProfileManager() {
        return this.profileManager;
    }

    public ArenaManager getArenaManager() {
        return this.arenaManager;
    }

    private void loadManagers() {
        this.profileManager = new ProfileManager();
        this.arenaManager = new ArenaManager();
    }

}
