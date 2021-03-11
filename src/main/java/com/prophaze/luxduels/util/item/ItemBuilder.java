package com.prophaze.luxduels.util.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.prophaze.luxduels.util.Messenger.color;

/**
 * Author: Zilleyy
 * Date: 25/02/2021 @ 7:21 pm AEST
 */
public class ItemBuilder {

    private final ItemStack item;

    /**
     * ItemBuilder constructor, defaults to a stack size of 1.
     *
     * @param material The initial material of the ItemStack.
     */
    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
    }

    /**
     * @param material The initial material of the ItemStack.
     * @param amount The initial stack size of the ItemStack.
     */
    public ItemBuilder(Material material, int amount) {
        this.item = new ItemStack(material, amount);
    }

    /**
     * @param item The initial item to use.
     */
    public ItemBuilder(ItemStack item) {
        this.item = item;
    }

    public static ItemBuilder of(Material material) {
        return new ItemBuilder(material);
    }

    public static ItemBuilder of(Material material, int amount) {
        return new ItemBuilder(material, amount);
    }

    public static ItemBuilder of(ItemStack item) {
        return new ItemBuilder(item);
    }

    /**
     * @return The ItemStack that has been created using the ItemBuilder.
     */
    public ItemStack build() {
        return item;
    }

    public ItemMeta getItemMeta() {
        return item.getItemMeta();
    }

    public TaggedItemStack getTaggedItemStack() {
        return new TaggedItemStack(item);
    }

    /**
     * @param displayName The text to set the item name to.
     * @return The ItemBuilder.
     */
    public ItemBuilder setDisplayName(String displayName) {
        ItemMeta meta = getItemMeta();

        meta.setDisplayName(color(displayName));
        item.setItemMeta(meta);

        return this;
    }

    /**
     * Strips the custom display name off the item,
     * leaving it with its default name.
     * @return The ItemBuilder.
     */
    public ItemBuilder stripDisplayName() {
        ItemMeta a = getItemMeta();
        ItemMeta b = new ItemStack(item.getType(), item.getAmount()).getItemMeta();

        b.setLore(a.getLore());
        b.setAttributeModifiers(a.getAttributeModifiers());
        b.setCustomModelData(a.getCustomModelData());

        b.addItemFlags(a.getItemFlags().toArray(new ItemFlag[0]));

        Map<Enchantment, Integer> enchantments = a.getEnchants();
        for(Enchantment enchantment : enchantments.keySet()) {
            b.addEnchant(enchantment, enchantments.get(enchantment), true);
        }

        b.setUnbreakable(a.isUnbreakable());

        item.setItemMeta(b);

        return this;
    }

    /**
     * @param lines The strings to set as the item's lore.
     * @return The ItemBuilder.
     */
    public ItemBuilder setLore(String... lines) {
        if(lines == null) return this;

        ItemMeta meta = getItemMeta();
        List<String> lore = new ArrayList<>();

        for(String line : lines) {
            lore.add(color(line));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param lines The strings to add to the item's lore.
     * @return The ItemBuilder.
     */
    public ItemBuilder addLore(String... lines) {
        if(lines == null) return this;

        ItemMeta meta = getItemMeta();
        List<String> lore = getItemMeta().hasLore() ? getItemMeta().getLore() : new ArrayList<>();

        for(String line : lines) {
            lore.add(color(line));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        return this;
    }

    public ItemBuilder setGlowing() {
        ItemMeta meta = getItemMeta();

        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        this.item.setItemMeta(meta);

        return this;
    }

}
