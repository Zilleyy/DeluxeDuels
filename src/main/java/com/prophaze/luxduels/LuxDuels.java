package com.prophaze.luxduels;

import com.prophaze.luxduels.arena.ArenaManager;
import com.prophaze.luxduels.profile.ProfileManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class LuxDuels extends JavaPlugin {

    @Getter public static LuxDuels instance;

    @Getter private ProfileManager profileManager;
    @Getter private ArenaManager arenaManager;

    @Override
    public void onEnable() {
        instance = this;

        this.loadManagers();
    }

    private void loadManagers() {
        this.profileManager = new ProfileManager();
        this.arenaManager = new ArenaManager();
    }

}
