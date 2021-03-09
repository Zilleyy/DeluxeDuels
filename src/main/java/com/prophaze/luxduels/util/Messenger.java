package com.prophaze.luxduels.util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 11:06 am AEST
 */
public class Messenger {

    public static void send(CommandSender sender, String message) {
        sender.sendMessage(color(message));
    }

    public static void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(color(message)));
    }

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
