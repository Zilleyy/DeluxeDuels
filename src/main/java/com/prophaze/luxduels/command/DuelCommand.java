package com.prophaze.luxduels.command;

import com.prophaze.luxduels.match.Match;
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
        send(sender, "&7----= &e&lDUELS HELP &7=----");
        send(sender, "/duel help - Displays this help");
        send(sender, "/duel join <type> - Joins the queue of match type <type>");
        send(sender, "/duel invite <player>");

    }

    @Default
    public static void onHelpCommand(CommandSender sender, @ALiteralArgument("help") String arg) {
        onBaseCommand(sender);
    }

    @Subcommand("join")
    public static void onJoinCommand(CommandSender sender, @AMultiLiteralArgument({"CASUAL", "COMP", "SHIELD", "NETHERITE", "DIAMOND", "OVERPOWERED", "POTION", "UHC", "CUSTOM"}) String arg) {
        Profile profile = ProfileManager.getProfileByUUID(((Player) sender).getUniqueId());
        if(!Queue.isInQueue(profile)) {
            Queue.addProfile(MatchType.valueOf(arg), profile);
            send(sender, "&bYou joined the queue for &e" + arg + "&b. &7(" + Queue.getPositionOf(profile) + "&8/" + Queue.getSize(MatchType.valueOf(arg)) + "&7)");
        } else {
            send(sender, "&bYou are already in the queue! Do &e/duel quit &bto leave the queue.");
        }
    }

}
