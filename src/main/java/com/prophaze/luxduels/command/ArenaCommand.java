package com.prophaze.luxduels.command;

import com.prophaze.luxduels.arena.Arena;
import com.prophaze.luxduels.arena.ArenaManager;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.NeedsOp;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.AIntegerArgument;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.prophaze.luxduels.util.Messenger.send;

/**
 * Author: Zilleyy
 * <br>
 * Date: 12/03/2021 @ 12:22 pm AEST
 */
@Command("arena")
@NeedsOp
public class ArenaCommand {

    @Subcommand("create")
    public static void createArenaCommand(CommandSender sender) {
        Player player = (Player) sender;
        Arena arena = ArenaManager.createAndGet();
        player.teleport(arena.getCuboid().getCenter());
        send(sender, "&eTeleported you to the center of the Arena, please set player spawn locations");
        send(sender, "&7/");
    }

    @Subcommand("setspawn")
    public static void setSpawn(CommandSender sender, @AIntegerArgument int profileNumber) {
        Player player = (Player) sender;
        Arena arena = ArenaManager.getArenaContaining(player.getLocation());
        if(arena != null) {
            arena.setSpawnPos(player.getLocation(), profileNumber);
            send(sender, "Set location " + profileNumber + " to current location");
        }
    }

    @Subcommand("world")
    public static void worldCommand(CommandSender sender) {
        Player player = (Player) sender;
        Location location = player.getLocation();
        location.setWorld(Bukkit.getWorld("arenas"));
        player.teleport(location);
    }

}
