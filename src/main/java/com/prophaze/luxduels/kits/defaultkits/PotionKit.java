package com.prophaze.luxduels.kits.defaultkits;

import com.prophaze.luxduels.kits.Kit;
import com.prophaze.luxduels.util.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class PotionKit extends Kit {

    public void apply(Player player) {
        player.getInventory().setHeldItemSlot(0);
        player.getInventory().setContents(kitInventory().getContents());
        player.getInventory().setItemInOffHand(offHand());
        player.getInventory().setArmorContents(armorContents());
    }

    public ItemStack kitIcon() {
        return new ItemBuilder(Material.SPLASH_POTION).addPotionEffect(PotionEffectType.HEAL, 1, 1, Color.RED).setDisplayName("&cPotion Duels").build();
    }

    public Inventory kitInventory() {
        Inventory inv = Bukkit.createInventory(null, InventoryType.PLAYER);
        inv.setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 5).addEnchantment(Enchantment.SWEEPING_EDGE, 3).build());
        // Confused by potion info in doc, will have to talk to AP tomorrow.
        return inv;
    }

    public String getKitName() {
        return "Potion";
    }

    public ItemStack offHand() {
        return new ItemStack(Material.GOLDEN_CARROT, 64);
    }

    public ItemStack[] armorContents() {
        ItemStack[] contents = new ItemStack[4];
        contents[3] = new ItemBuilder(Material.DIAMOND_HELMET)
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build();
        contents[2] = new ItemBuilder(Material.DIAMOND_CHESTPLATE)
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build();
        contents[1] = new ItemBuilder(Material.DIAMOND_LEGGINGS)
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build();
        contents[0] = new ItemBuilder(Material.DIAMOND_BOOTS)
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build();
        return contents;
    }

}
