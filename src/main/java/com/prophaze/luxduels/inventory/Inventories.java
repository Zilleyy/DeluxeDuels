package com.prophaze.luxduels.inventory;

import com.prophaze.luxduels.LuxDuels;
import com.prophaze.luxduels.util.Messenger;
import fr.minuskube.inv.SmartInventory;

public class Inventories {

    public static SmartInventory MatchTypeInventory = SmartInventory.builder()
            .id("MatchTypeInventory")
            .provider(new MatchTypeInventory())
            .manager(LuxDuels.inventoryManager)
            .size(1, 9)
            .title(Messenger.color("&ePlease select your Kit."))
            .build();

}
