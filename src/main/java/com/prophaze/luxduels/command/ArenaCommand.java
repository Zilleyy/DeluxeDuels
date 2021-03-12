package com.prophaze.luxduels.command;

import com.prophaze.luxduels.arena.ArenaManager;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.NeedsOp;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import dev.jorel.commandapi.annotations.arguments.AStringArgument;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Author: Zilleyy
 * <br>
 * Date: 12/03/2021 @ 12:22 pm AEST
 */
@Command("arena")
@NeedsOp
public class ArenaCommand {

    @Subcommand("create")
    public static void createArenaCommand(CommandSender sender, @AStringArgument String name, @AStringArgument String schematic) {
        Player player = (Player) sender;
        Location location = player.getLocation();
        ArenaManager.createArena(name, schematic, location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Subcommand("world")
    public static void worldCommand(CommandSender sender) {
        Player player = (Player) sender;
        Location location = player.getLocation();
        location.setWorld(Bukkit.getWorld("arenas"));
        player.teleport(location);
    }

}
