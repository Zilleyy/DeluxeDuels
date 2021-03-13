package com.prophaze.luxduels.kits;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class Kit {

    public abstract void apply(Player player);

    public abstract ItemStack kitIcon();

    public abstract String getKitName();

    public abstract Inventory kitInventory();

    public abstract ItemStack offHand();

    public abstract ItemStack[] armorContents();

}