package com.prophaze.luxduels.command;

import com.prophaze.luxduels.match.MatchType;
import com.prophaze.luxduels.profile.Profile;
import com.prophaze.luxduels.profile.ProfileManager;
import com.prophaze.luxduels.queue.Queue;
import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.ALiteralArgument;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.prophaze.luxduels.util.Messenger.send;

/**
 * Author: Zilleyy
 * <br>
 * Date: 10/03/2021 @ 5:42 pm AEST
 */
@Command("duel")
@Alias("duels")
public class DuelCommand {

    private String[] types = MatchType.getStringValues();

    @Default
    public static void onBaseCommand(CommandSender sender) {
        send(sender, "&8********************** &e&lDUELS HELP &8**********************");
        send(sender, "&7/duel <player> &8- &7Invite a player to duel with you.");
        send(sender, "&7/duel help &8- &7Displays this help.");
        send(sender, "&7/duel join <type> &8- &7Joins the queue of match type <type>.");
        send(sender, "&7/duel leave &8- &7Leaves the queue you are in.");
        send(sender, "&8**************************************************************");
    }

    @Default
    public static void onHelpCommand(CommandSender sender, @ALiteralArgument("help") String arg) {
        onBaseCommand(sender);
    }

    @Default
    public static void onDuelCommand(CommandSender sender, @APlayerArgument Player player) {

    }

    @Subcommand("join")
    public static void onJoinCommand(CommandSender sender, @AMultiLiteralArgument({"SHIELD", "NETHERITE", "DIAMOND", "OVERPOWERED", "POTION", "UHC", "CUSTOM"}) String arg) {
        Profile profile = ProfileManager.getProfile(((Player) sender).getUniqueId());
        if(!Queue.inQueue(profile)) {
            Queue.addProfile(MatchType.valueOf(arg), profile);
            send(sender, "&7You joined the queue for &e" + arg + "&7. &8(&7" + Queue.getPositionOf(profile) + "&8/&7" + Queue.getSize(MatchType.valueOf(arg)) + "&8)");
        } else {
            send(sender, "&7You are already in the queue! Do &e/duel leave &7to leave the queue.");
        }
    }

    @Subcommand("leave")
    public static void onLeaveCommand(CommandSender sender) {
        Profile profile = ProfileManager.getProfile((Player) sender);
        if(!Queue.inQueue(profile)) {
            send(profile, "&cYou are not in a queue yet! Type /duel join <type> to join a duel.");
        } else {
            Queue.removeProfile(profile);
            send(profile, "&aSuccessfully left the queue.");
        }
    }

}
