package com.prophaze.luxduels;

import com.prophaze.luxduels.arena.ArenaManager;
import com.prophaze.luxduels.command.ArenaCommand;
import com.prophaze.luxduels.command.BuilderCommand;
import com.prophaze.luxduels.command.DuelCommand;
import com.prophaze.luxduels.event.PlayerListener;
import com.prophaze.luxduels.file.Yaml;
import com.prophaze.luxduels.kits.KitManager;
import com.prophaze.luxduels.profile.ProfileManager;
import com.prophaze.luxduels.task.MatchHandler;
import com.prophaze.luxduels.task.QueueHandler;
import dev.jorel.commandapi.CommandAPI;
import fr.minuskube.inv.InventoryManager;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class LuxDuels extends JavaPlugin {

    @Getter public static LuxDuels instance;
    public static InventoryManager inventoryManager;

    private QueueHandler queuehandler;
    private MatchHandler matchhandler;

    @Getter private Yaml arenaFile;

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

        this.loadFiles();
        this.registerEvents();
        ProfileManager.loadProfiles();
        ArenaManager.loadArenas();
        KitManager.loadServerKits();
        this.loadRunnables();
        inventoryManager = new InventoryManager(this);
        inventoryManager.init();

        new File(getDataFolder(), "Schematics").mkdir();
    }

    private void loadFiles() {
        this.arenaFile = new Yaml("Arenas", this.getDataFolder().getAbsolutePath());
        // havent setup other files.
    }

    private void registerCommands() {
        final Class<?>[] classes = new Class[] {
                DuelCommand.class,
                ArenaCommand.class,
                BuilderCommand.class
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
        this.matchhandler = new MatchHandler();
        this.queuehandler = new QueueHandler();

        this.queuehandler.runTaskTimer(this, 0L, 0L);
        this.matchhandler.runTaskTimer(this, 0L, 0L);
    }

}
