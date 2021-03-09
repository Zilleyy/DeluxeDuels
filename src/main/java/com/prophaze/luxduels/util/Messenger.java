package com.prophaze.luxduels.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 11:06 am AEST
 */
public class Messenger {

    public static void send(Player player, String message) {

    }

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
