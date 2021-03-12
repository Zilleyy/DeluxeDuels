package com.prophaze.luxduels.kits;

import com.prophaze.luxduels.kits.defaultkits.*;

import java.util.ArrayList;
import java.util.List;

public class KitManager {

    private static final List<Kit> serverKits = new ArrayList<>();

    public static void loadServerKits() {
        serverKits.add(new NetheriteKit());
        serverKits.add(new OverpoweredKit());
        serverKits.add(new PotionKit());
        serverKits.add(new ShieldKit());
        serverKits.add(new UHCKit());
    }

    public static Kit getKit(String kitName) {
        return serverKits.stream().filter(kit -> kit.getKitName().equalsIgnoreCase(kitName)).findFirst().orElse(null);
    }
}
