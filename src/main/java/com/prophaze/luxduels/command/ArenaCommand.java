package com.prophaze.luxduels.command;

import com.prophaze.luxduels.arena.Arena;
import com.prophaze.luxduels.arena.ArenaManager;
import com.prophaze.luxduels.util.world.LocationUtil;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.NeedsOp;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.AIntegerArgument;
import dev.jorel.commandapi.annotations.arguments.AStringArgument;
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

    @Default
    public static void onBaseCommand(CommandSender sender) {
        send(sender, "&8********************** &e&lARENA HELP &8**********************");
        send(sender, "&7/arena create <schematicName> &8- &7Create an Arena utilizing the selected schematic.");
        send(sender, "&7/arena setspawn <1|2> &8- &7Sets the spawnpoint of the player's.");
        send(sender, "&7/arena world &8- &7Teleport to the Arena world.");
        send(sender, "&7/duel leave &8- &7Leaves the queue you are in.");
        send(sender, "&8**************************************************************");
    }

    @Subcommand("create")
    public static void createArenaCommand(CommandSender sender, @AStringArgument String arenaName) {
        Player player = (Player) sender;
        Arena arena = ArenaManager.createAndGet(arenaName);
        if(arena != null) {
            player.teleport(arena.getCuboid().getCenter());
            send(sender, "&eTeleported you to the center of the Arena, please set player spawn locations");
        } else send(sender, "&cThat schematic wasn't found, this is caps sensitive and doesn't need the file ending (.schematic)!");
    }

    @Subcommand("setspawn")
    public static void setSpawn(CommandSender sender, @AIntegerArgument int profileNumber) {
        Player player = (Player) sender;
        Arena arena = ArenaManager.getArenaContaining(player.getLocation());
        if(arena != null) {
            if(profileNumber > 0 && profileNumber < 3) {
                arena.setSpawnPos(player.getLocation(), profileNumber);
                ArenaManager.setString(arena.getUUID() + ".locs.loc" + profileNumber, LocationUtil.locationToString(player.getLocation().getBlock().getLocation(), true));
                send(sender, "Set location " + profileNumber + " to current location");
            } else {
                send(sender, "Please select spawnpoint 1 or 2.");
            }
        } else send(sender, "You must be in an Arena to set its spawnpoint.");
    }

    @Subcommand("world")
    public static void worldCommand(CommandSender sender) {
        Player player = (Player) sender;
        Location location = player.getLocation();
        location.setWorld(Bukkit.getWorld("arenas"));
        player.teleport(location);
    }

}
