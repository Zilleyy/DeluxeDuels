package com.prophaze.luxduels.kits.defaultkits;

import com.prophaze.luxduels.kits.Kit;
import com.prophaze.luxduels.util.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class UHCKit extends Kit {

    public void apply(Player player) {
        player.getInventory().setHeldItemSlot(0);
        player.getInventory().setContents(kitInventory().getContents());
        player.getInventory().setItemInOffHand(offHand());
        player.getInventory().setArmorContents(armorContents());
    }

    public ItemStack kitIcon() {
        return new ItemBuilder(Material.GOLDEN_APPLE).setDisplayName("&eUHC Duels").build();
    }

    public Inventory kitInventory() {
        Inventory inv = Bukkit.createInventory(null, InventoryType.PLAYER);
        inv.setItem(0, new ItemStack(Material.DIAMOND_AXE));
        inv.setItem(1, new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 1).build());
        inv.setItem(2, new ItemStack(Material.GOLDEN_APPLE, 4));
        inv.setItem(3, new ItemStack(Material.BOW, 1));
        inv.setItem(4, new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 1).build());
        inv.setItem(5, new ItemStack(Material.COBWEB, 16));
        inv.setItem(6, new ItemStack(Material.LAVA_BUCKET));
        inv.setItem(7, new ItemStack(Material.WATER_BUCKET));
        inv.setItem(8, new ItemStack(Material.COBBLESTONE, 64));
        inv.setItem(9, new ItemStack(Material.OAK_PLANKS, 64));
        inv.setItem(17, new ItemStack(Material.ARROW, 24));
        return inv;
    }

    public String getKitName() {
        return "UHC";
    }

    public ItemStack offHand() {
        return new ItemStack(Material.SHIELD);
    }

    public ItemStack[] armorContents() {
        ItemStack[] contents = new ItemStack[4];
        contents[3] = new ItemBuilder(Material.IRON_HELMET).addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1).build();
        contents[2] = new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build();
        contents[1] = new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build();
        contents[0] = new ItemBuilder(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1).build();
        return contents;
    }

}
