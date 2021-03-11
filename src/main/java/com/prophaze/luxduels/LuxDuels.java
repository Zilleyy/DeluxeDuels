package com.prophaze.luxduels;

import com.prophaze.luxduels.arena.ArenaManager;
import com.prophaze.luxduels.command.DuelCommand;
import com.prophaze.luxduels.event.PlayerListener;
import com.prophaze.luxduels.match.MatchManager;
import com.prophaze.luxduels.profile.Profile;
import com.prophaze.luxduels.profile.ProfileManager;
import com.prophaze.luxduels.task.MatchHandler;
import com.prophaze.luxduels.task.QueueHandler;
import dev.jorel.commandapi.CommandAPI;
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

public class LuxDuels extends JavaPlugin {

    @Getter public static LuxDuels instance;

    private QueueHandler queuehandler;
    private MatchHandler matchhandler;

    @Override
    public void onLoad() {
        CommandAPI.onLoad(false);
        registerCommands();
    }

    @Override
    public void onEnable() {
        instance = this;

        // MUST RUN BEFORE ANY OTHER METHODS.
        CommandAPI.onEnable(this);

        this.registerEvents();
        ProfileManager.loadProfiles();
        ArenaManager.loadArenas();
        this.loadRunnables();
    }

    private void registerCommands() {
        final Class<?>[] classes = new Class[] {
                DuelCommand.class
        };

        for(final Class<?> clazz : classes) {
            CommandAPI.registerCommand(clazz);
        }

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
        final Class<Listener>[] listeners = new Class[] {
                PlayerListener.class
        };

        PluginManager pm = Bukkit.getPluginManager();
        for(Class<Listener> listener : listeners) {
            try {
                pm.registerEvents(listener.getConstructor().newInstance(), this);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ignore) {

            }
        }
    }

    private void loadRunnables() {
        this.queuehandler = new QueueHandler();
        this.matchhandler = new MatchHandler();

        this.queuehandler.runTaskTimerAsynchronously(this, 0L, 0L);
        this.matchhandler.runTaskTimerAsynchronously(this, 0L, 0L);
    }

}
