package com.prophaze.luxduels.util.item;

import net.minecraft.server.v1_16_R3.ItemStack;
import net.minecraft.server.v1_16_R3.NBTBase;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;

/**
 * Author: Zilleyy
 * <br>
 * Date: 26/02/2021 @ 9:41 am AEST
 */
public class TaggedItemStack {

    private ItemStack item;

    /**
     * Creates a new {@link TaggedItemStack}
     * @param item {@link net.minecraft.server.v1_16_R3.ItemStack}
     */
    public TaggedItemStack(ItemStack item) {
        this.item = item;
    }

    /**
     * Creates a new {@link TaggedItemStack}
     * @param item {@link org.bukkit.inventory.ItemStack}
     */
    public TaggedItemStack(org.bukkit.inventory.ItemStack item) {
        this.item = CraftItemStack.asNMSCopy(item);
    }

    /**
     * @param item {@link net.minecraft.server.v1_16_R3.ItemStack}
     * @return A new {@link TaggedItemStack}
     */
    public static TaggedItemStack of(ItemStack item) {
        return new TaggedItemStack(item);
    }

    /**
     * @param item {@link org.bukkit.inventory.ItemStack}
     * @return A new {@link TaggedItemStack}
     */
    public static TaggedItemStack of(org.bukkit.inventory.ItemStack item) {
        return new TaggedItemStack(item);
    }

    /**
     * @return The {@link NBTTagCompound} of the item.
     */
    public NBTTagCompound getTag() {
        return this.item.getOrCreateTag();
    }

    /**
     * Adds a string of a specified key to the tagged item.
     * @param key The key of the value.
     * @param value The value of the key.
     * @return The {@link TaggedItemStack}.
     */
    public TaggedItemStack addString(String key, String value) {
        getTag().setString(key, value);
        return this;
    }

    /**
     * Adds an integer of a specified key to the tagged item.
     * @param key The key of the value.
     * @param value The value of the key.
     * @return The {@link TaggedItemStack}.
     */
    public TaggedItemStack addInteger(String key, Integer value) {
        getTag().setInt(key, value);
        return this;
    }

    /**
     * Adds a boolean of a specified key to the tagged item.
     * @param key The key of the value.
     * @param value The value of the key.
     * @return The {@link TaggedItemStack}.
     */
    public TaggedItemStack addBoolean(String key, Boolean value) {
        getTag().setBoolean(key, value);
        return this;
    }

    /**
     * Removes the tag of a specified key from the tagged item.
     * @return The {@link TaggedItemStack}.
     */
    public TaggedItemStack removeTag(String key) {
        item.removeTag(key);
        return this;
    }

    /**
     * Removes a key from the tagged item.
     * @return The {@link TaggedItemStack}.
     */
    public TaggedItemStack removeKey(String key) {
        getTag().remove(key);
        return this;
    }

    /**
     * @return Whether or not the item has a tag.
     */
    public boolean hasTag() {
        return this.item.hasTag();
    }

    /**
     * @param key The key to check.
     * @return Whether or not the item has a value of the given key.
     */
    public boolean hasKey(String key) {
        return getTag().hasKey(key);
    }

    /**
     * Used to get the NBTBase, the NBTBase can then
     * Be casted to a specific type of tag.
     *
     * @param key The key used to locate the value.
     * @return The {@link NBTBase} of the inputted key.
     */
    public NBTBase getBase(String key) {
        return getTag().get(key);
    }

    /**
     * Similar to {@link #getBase(String)}, however,
     * The type of value must be defined.
     *
     * @param key The key used to locate the value.
     * @return The value of the inputted key of the defined tag type.
     */
    public <T> T getValue(String key, Class<? extends NBTBase> T) {
        return (T) getTag().get(key);
    }

    /**
     * @param key The key used to locate the value.
     * @return The string value of the inputted key.
     */
    public String getString(String key) {
        return getTag().getString(key);
    }

    /**
     * @param key The key used to locate the value.
     * @return The integer value of the inputted key.
     */
    public Integer getInteger(String key) {
        return getTag().getInt(key);
    }

    /**
     * @param key The key used to locate the value.
     * @return The boolean value of the inputted key.
     */
    public Boolean getBoolean(String key) {
        return getTag().getBoolean(key);
    }

}
