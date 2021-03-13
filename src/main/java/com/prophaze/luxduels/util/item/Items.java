package com.prophaze.luxduels.util.item;

import com.prophaze.luxduels.util.Messenger;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Items {

    public static final ItemStack CASUAL = new ItemBuilder(Material.IRON_SWORD).setDisplayName(Messenger.color("&eCasual Queue")).build();
    public static final ItemStack COMP = new ItemBuilder(Material.NETHERITE_SWORD).setDisplayName(Messenger.color("&aCompetitive Queue")).build();
    public static final ItemStack PARTY = new ItemBuilder(Material.GOLDEN_AXE).setDisplayName(Messenger.color("&dParty Fights")).build();
    public static final ItemStack SPECTATOR = new ItemBuilder(Material.CLOCK).setDisplayName(Messenger.color("&6Spectator Mode")).build();
    public static final ItemStack EDITOR = new ItemBuilder(Material.ENCHANTED_BOOK).setDisplayName(Messenger.color("&7Kit Editor")).build();
    public static final ItemStack LEAVE_QUEUE = ItemBuilder.of(Material.ORANGE_DYE).setDisplayName("&c&lLeave Queue &7(click)").setGlowing().build();

    public static void setServerItems(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getInventory().setItem(0, CASUAL);
        player.getInventory().setItem(1, COMP);
        player.getInventory().setItem(2, PARTY);
        player.getInventory().setItem(7, SPECTATOR);
        player.getInventory().setItem(8, EDITOR);
    }


}
