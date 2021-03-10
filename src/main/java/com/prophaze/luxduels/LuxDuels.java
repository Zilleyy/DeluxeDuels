package com.prophaze.luxduels;

import com.prophaze.luxduels.event.PlayerListener;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;

public class LuxDuels extends JavaPlugin {

    @Getter public static LuxDuels instance;

    @Override
    public void onEnable() {
        instance = this;

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

}
