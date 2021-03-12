package com.prophaze.luxduels.command;

import com.prophaze.luxduels.util.item.Items;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.NeedsOp;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.prophaze.luxduels.util.Messenger.send;

@Command("builder")
@NeedsOp
public class BuilderCommand {

    @Getter public static final List<UUID> builders = new ArrayList<>();

    @Default
    public static void onBaseCommand(CommandSender sender) {
        if(sender instanceof Player) {
            Player player = (Player)sender;
            if(!builders.contains(player.getUniqueId())) {
                builders.add(player.getUniqueId());
                player.getInventory().clear();
                send(sender, "&6Builder mode &aEnabled!");
            } else {
                builders.remove(player.getUniqueId());
                Items.setServerItems(player);
                send(sender, "&6Builder mode &cDisabled!");
            }
        } else send(sender, "You cannot go into build mode unless you're a player.");
    }

}
