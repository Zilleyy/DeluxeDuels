package com.prophaze.luxduels;

import com.prophaze.luxduels.arena.ArenaManager;
import com.prophaze.luxduels.event.PlayerListener;
import com.prophaze.luxduels.match.MatchManager;
import com.prophaze.luxduels.profile.ProfileManager;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;

public class LuxDuels extends JavaPlugin {

    @Getter public static LuxDuels instance;

    @Getter private ProfileManager profileManager;
    @Getter private ArenaManager arenaManager;
    @Getter private MatchManager matchManager;

    @Override
    public void onEnable() {
        instance = this;

        this.loadManagers();
        this.registerEvents();
    }

    /**
     * Registers all defined events, classes must have a constructor
     * with no parameters otherwise an error will be thrown.
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @SneakyThrows
    private void registerEvents() {
        Class<Listener>[] listeners = new Class[] {
                PlayerListener.class
        };

        PluginManager pm = Bukkit.getPluginManager();
        for(Class<Listener> listener : listeners) {
            pm.registerEvents(listener.getConstructor().newInstance(), this);
        }
    }

    private void loadManagers() {
        this.profileManager = new ProfileManager();
        this.arenaManager = new ArenaManager();
        this.matchManager = new MatchManager();
    }

}
