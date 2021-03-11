package com.prophaze.luxduels;

import com.prophaze.luxduels.util.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Author: Zilleyy
 * <br>
 * Date: 11/03/2021 @ 10:21 am AEST
 */
public class Constant {

    /* ITEMS */
    public static final ItemStack LEAVE_QUEUE = ItemBuilder.of(Material.ORANGE_DYE)
            .setDisplayName("&c&lLeave Queue &7(click)")
            .setGlowing()
            .build();

}
