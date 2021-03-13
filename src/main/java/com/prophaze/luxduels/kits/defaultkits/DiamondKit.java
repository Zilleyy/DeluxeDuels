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

public class DiamondKit extends Kit {

    public void apply(Player player) {
        player.getInventory().setHeldItemSlot(0);
        player.getInventory().setContents(kitInventory().getContents());
        player.getInventory().setItemInOffHand(offHand());
        player.getInventory().setArmorContents(armorContents());
    }

    public ItemStack kitIcon() {
        return new ItemBuilder(Material.END_CRYSTAL).setDisplayName("&bDiamond Crystal").build();
    }

    public Inventory kitInventory() {
        Inventory inv = Bukkit.createInventory(null, InventoryType.PLAYER);
        inv.setItem(0, new ItemBuilder(Material.DIAMOND_SWORD)
                .addEnchantment(Enchantment.DAMAGE_ALL, 5)
                .addEnchantment(Enchantment.KNOCKBACK, 2)
                .addEnchantment(Enchantment.SWEEPING_EDGE, 3).build());

        inv.setItem(1, new ItemStack(Material.ENDER_PEARL, 16));

        inv.setItem(2, new ItemBuilder(Material.CROSSBOW)
                .addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1)
                .addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 3)
                .addUnsafeEnchantment(Enchantment.MULTISHOT, 1).build());

        inv.setItem(3, new ItemStack(Material.GOLDEN_APPLE, 64));
        inv.setItem(4, new ItemStack(Material.OBSIDIAN, 64));
        inv.setItem(5, new ItemStack(Material.END_CRYSTAL, 64));
        inv.setItem(6, new ItemStack(Material.TOTEM_OF_UNDYING, 1));
        inv.setItem(7, new ItemBuilder(Material.SPLASH_POTION).setDisplayName("&fSplash Potion of Swiftness").addPotionEffect(PotionEffectType.SPEED, 90, 2, Color.GRAY).build());
        inv.setItem(8, new ItemBuilder(Material.SPLASH_POTION).setDisplayName("&fSplash Potion of Strength").addPotionEffect(PotionEffectType.INCREASE_DAMAGE, 90, 2, Color.RED).build());

        for(int i = 9; i < 13; i++) inv.setItem(i, new ItemStack(Material.ENDER_PEARL, 16));

        for(int i = 13; i < 15; i++) inv.setItem(i, new ItemBuilder(Material.SPLASH_POTION).setDisplayName("&fSplash Potion of the Turtle Master")
                .addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 3, Color.GRAY)
                .addPotionEffect(PotionEffectType.SLOW, 20, 4, Color.GRAY)
                .build());

        for(int i = 15; i < 17; i++) inv.setItem(i, new ItemStack(Material.EXPERIENCE_BOTTLE, 64));

        for(int i = 17; i < 20; i++) inv.setItem(i, new ItemStack(Material.TOTEM_OF_UNDYING));

        inv.setItem(20, new ItemBuilder(Material.TIPPED_ARROW, 64)
                .setDisplayName("&fArrow of Slow Falling")
                .addPotionEffect(PotionEffectType.SLOW_FALLING,  249, 1, Color.WHITE).build());

        inv.setItem(21, new ItemStack(Material.ARROW));

        for(int i = 22; i < 28; i++) inv.setItem(i, new ItemBuilder(Material.SPLASH_POTION).setDisplayName("&fSplash Potion of Swiftness").addPotionEffect(PotionEffectType.SPEED, 90, 2, Color.GRAY).build());

        for(int i = 28; i < 31; i++) inv.setItem(i, new ItemBuilder(Material.SPLASH_POTION).setDisplayName("&fSplash Potion of Strength").addPotionEffect(PotionEffectType.INCREASE_DAMAGE, 90, 2, Color.PURPLE).build());

        return inv;
    }

    public String getKitName() {
        return "Diamond";
    }

    public ItemStack offHand() {
        return new ItemStack(Material.TOTEM_OF_UNDYING);
    }

    public ItemStack[] armorContents() {
        ItemStack[] contents = new ItemStack[4];
        contents[3] = new ItemBuilder(Material.DIAMOND_HELMET)
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchantment(Enchantment.MENDING, 1)
                .addEnchantment(Enchantment.DURABILITY, 3).build();
        contents[2] = new ItemBuilder(Material.DIAMOND_CHESTPLATE)
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchantment(Enchantment.MENDING, 1)
                .addEnchantment(Enchantment.DURABILITY, 3).build();
        contents[1] = new ItemBuilder(Material.DIAMOND_LEGGINGS)
                .addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4)
                .addEnchantment(Enchantment.MENDING, 1)
                .addEnchantment(Enchantment.DURABILITY, 3).build();
        contents[0] = new ItemBuilder(Material.DIAMOND_BOOTS)
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchantment(Enchantment.MENDING, 1)
                .addEnchantment(Enchantment.DURABILITY, 3).build();
        return contents;
    }
}
