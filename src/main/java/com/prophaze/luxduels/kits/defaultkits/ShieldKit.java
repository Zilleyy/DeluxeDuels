package com.prophaze.luxduels.kits.defaultkits;

import com.prophaze.luxduels.kits.Kit;
import com.prophaze.luxduels.util.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShieldKit extends Kit {

    public void apply(Player player) {
        player.getInventory().setHeldItemSlot(0);
        player.getInventory().setContents(kitInventory().getContents());
        player.getInventory().setItemInOffHand(offHand());
        player.getInventory().setArmorContents(armorContents());
    }

    public ItemStack kitIcon() {
        return new ItemBuilder(Material.SHIELD).setDisplayName("&9Shield").build();
    }

    public Inventory kitInventory() {
        Inventory inv = Bukkit.createInventory(null, InventoryType.PLAYER);
        inv.setItem(0, new ItemStack(Material.DIAMOND_SWORD));
        inv.setItem(1, new ItemStack(Material.DIAMOND_AXE));
        inv.setItem(2, new ItemStack(Material.CROSSBOW));
        inv.setItem(8, new ItemStack(Material.COOKED_BEEF, 1));
        inv.setItem(17, new ItemStack(Material.ARROW, 5));
        return inv;
    }

    public String getKitName() {
        return "Shield";
    }

    public ItemStack offHand() {
        return new ItemStack(Material.SHIELD);
    }

    public ItemStack[] armorContents() {
        ItemStack[] contents = new ItemStack[4];
        contents[3] = new ItemStack(Material.DIAMOND_HELMET);
        contents[2] = new ItemStack(Material.DIAMOND_CHESTPLATE);
        contents[1] = new ItemStack(Material.DIAMOND_LEGGINGS);
        contents[0] = new ItemStack(Material.DIAMOND_BOOTS);
        return contents;
    }

}
