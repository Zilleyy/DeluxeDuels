package com.prophaze.luxduels.command;

import com.prophaze.luxduels.arena.ArenaManager;
import com.prophaze.luxduels.kits.Kit;
import com.prophaze.luxduels.kits.KitManager;
import com.prophaze.luxduels.match.Match;
import com.prophaze.luxduels.match.MatchManager;
import com.prophaze.luxduels.profile.Profile;
import com.prophaze.luxduels.profile.ProfileManager;
import com.prophaze.luxduels.queue.Queue;
import com.prophaze.luxduels.util.Messenger;
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

    @Default
    public static void onBaseCommand(CommandSender sender) {
        send(sender, "&8********************** &e&lDUELS HELP &8**********************");
        send(sender, "&7/duel <player> &8- &7Invite a player to duel with you.");
        send(sender, "&7/duel accept &8- &7Accepts recent duel invite.");
        send(sender, "&7/duel help &8- &7Displays this help.");
        send(sender, "&7/duel leave &8- &7Leaves the queue you are in.");
        send(sender, "&8**************************************************************");
    }

    @Default
    public static void onHelpCommand(CommandSender sender, @ALiteralArgument("help") String arg) {
        onBaseCommand(sender);
    }

    @Default
    public static void onDuelCommand(CommandSender sender, @APlayerArgument Player target,  @AMultiLiteralArgument({"SHIELD", "NETHERITE", "DIAMOND", "OVERPOWERED", "POTION", "UHC"}) String arg) {
        Player player = (Player) sender;
        Profile senderProfile = ProfileManager.getProfile(player);

    }

    @Subcommand("accept")
    public static void onDuelAcceptCommand(CommandSender sender, @APlayerArgument Player target) {
        Match match = MatchManager.getMatch(ProfileManager.getProfile(target));
        Player player = (Player)sender;
        match.setProfileTwo(ProfileManager.getProfile(player));
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
