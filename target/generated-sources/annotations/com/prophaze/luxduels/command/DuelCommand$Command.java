package com.prophaze.luxduels.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.LiteralArgument;
import dev.jorel.commandapi.arguments.MultiLiteralArgument;

// This class was automatically generated by the CommandAPI
public class DuelCommand$Command {

    @SuppressWarnings("unchecked")
    public static void register() {

        new CommandAPICommand("duel")
            .withAliases("duels")
            .executes((sender, args) -> {
                DuelCommand.onBaseCommand(sender);
            })
            .register();

        new CommandAPICommand("duel")
            .withAliases("duels")
            .withArguments(new LiteralArgument("help").setListed(true))
            .executes((sender, args) -> {
                DuelCommand.onHelpCommand(sender, (String) args[0]);
            })
            .register();

        new CommandAPICommand("duel")
            .withArguments(
                new MultiLiteralArgument("join")
                    .setListed(false)
            )
            .withAliases("duels")
            .withArguments(new MultiLiteralArgument("CASUAL", "COMP", "SHIELD", "NETHERITE", "DIAMOND", "OVERPOWERED", "POTION", "UHC", "CUSTOM"))
            .executes((sender, args) -> {
                DuelCommand.onJoinCommand(sender, (String) args[0]);
            })
            .register();

        new CommandAPICommand("duel")
            .withArguments(
                new MultiLiteralArgument("leave")
                    .setListed(false)
            )
            .withAliases("duels")
            .executes((sender, args) -> {
                DuelCommand.onLeaveCommand(sender);
            })
            .register();

        }

}
