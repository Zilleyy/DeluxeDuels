package com.prophaze.luxduels;

import com.prophaze.luxduels.arena.ArenaManager;
import com.prophaze.luxduels.event.PlayerListener;
import com.prophaze.luxduels.match.MatchManager;
import com.prophaze.luxduels.profile.Profile;
import com.prophaze.luxduels.profile.ProfileManager;
import com.prophaze.luxduels.task.MatchHandler;
import com.prophaze.luxduels.task.QueueHandler;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class LuxDuels extends JavaPlugin {

    @Getter public static LuxDuels instance;

    private QueueHandler queuehandler;
    private MatchHandler matchhandler;

    @Override
    public void onEnable() {
        instance = this;

        this.registerEvents();
        this.loadProfiles();
        this.loadRunnables();
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

    private void loadProfiles() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            ProfileManager.loadProfile(player.getUniqueId());
        }
    }

    @SneakyThrows
    private void loadRunnables() {
        Class<? extends BukkitRunnable>[] runnables = new Class[] {
                MatchHandler.class,
                QueueHandler.class
        };

        for(Class<? extends BukkitRunnable> runnable : runnables) {
            Field field = this.getClass().getField(runnable.getSimpleName().toLowerCase());
            BukkitTask task = runnable.getConstructor().newInstance().runTaskTimerAsynchronously(this, 0L, 0L);
            field.set(BukkitTask.class, task);
        }
    }

}
