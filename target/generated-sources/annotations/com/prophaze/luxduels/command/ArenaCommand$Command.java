package com.prophaze.luxduels.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.MultiLiteralArgument;
import dev.jorel.commandapi.arguments.StringArgument;

// This class was automatically generated by the CommandAPI
public class ArenaCommand$Command {

    @SuppressWarnings("unchecked")
    public static void register() {

        new CommandAPICommand("arena")
            .withPermission(CommandPermission.OP)
            .executes((sender, args) -> {
                ArenaCommand.onBaseCommand(sender);
            })
            .register();

        new CommandAPICommand("arena")
            .withArguments(
                new MultiLiteralArgument("create")
                    .setListed(false)
            )
            .withPermission(CommandPermission.OP)
            .withArguments(new StringArgument("arenaSchem"))
            .executes((sender, args) -> {
                ArenaCommand.createArenaCommand(sender, (String) args[0]);
            })
            .register();

        new CommandAPICommand("arena")
            .withArguments(
                new MultiLiteralArgument("schems")
                    .setListed(false)
            )
            .withPermission(CommandPermission.OP)
            .executes((sender, args) -> {
                ArenaCommand.listArenaSchems(sender);
            })
            .register();

        new CommandAPICommand("arena")
            .withArguments(
                new MultiLiteralArgument("setspawn")
                    .setListed(false)
            )
            .withPermission(CommandPermission.OP)
            .withArguments(new IntegerArgument("profileNumber", -2147483648, 2147483647))
            .executes((sender, args) -> {
                ArenaCommand.setSpawn(sender, (int) args[0]);
            })
            .register();

        new CommandAPICommand("arena")
            .withArguments(
                new MultiLiteralArgument("world")
                    .setListed(false)
            )
            .withPermission(CommandPermission.OP)
            .executes((sender, args) -> {
                ArenaCommand.worldCommand(sender);
            })
            .register();

        }

}
