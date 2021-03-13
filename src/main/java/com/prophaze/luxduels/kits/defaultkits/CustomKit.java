package com.prophaze.luxduels.kits.defaultkits;

import com.prophaze.luxduels.kits.Kit;
import com.prophaze.luxduels.util.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CustomKit extends Kit {

    public void apply(Player player) {
        player.getInventory().setHeldItemSlot(0);
        player.getInventory().setContents(kitInventory().getContents());
        player.getInventory().setItemInOffHand(offHand());
        player.getInventory().setArmorContents(armorContents());
    }

    public ItemStack kitIcon() {
        return new ItemBuilder(Material.BARRIER).setDisplayName("&cDisabled for Beta (Custom Kit)").build();
    }

    public Inventory kitInventory() {
        Inventory inv = Bukkit.createInventory(null, InventoryType.PLAYER);
        return inv;
    }

    public String getKitName() {
        return "Custom";
    }

    public ItemStack offHand() {
        return new ItemStack(Material.AIR);
    }

    public ItemStack[] armorContents() {
        ItemStack[] contents = new ItemStack[4];
        return contents;
    }
}
