package com.prophaze.luxduels.inventory;

import com.prophaze.luxduels.kits.Kit;
import com.prophaze.luxduels.kits.KitManager;
import com.prophaze.luxduels.profile.Profile;
import com.prophaze.luxduels.profile.ProfileManager;
import com.prophaze.luxduels.queue.Queue;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotPos;
import org.bukkit.entity.Player;

import static com.prophaze.luxduels.util.Messenger.send;

public class MatchTypeInventory implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {
        for(int i = 0; i < KitManager.serverKits.size(); i++) {
            Profile profile = ProfileManager.getProfile(player);
            Kit kit = KitManager.serverKits.get(i);
            contents.set(SlotPos.of(0, i + 1), ClickableItem.of(kit.kitIcon(),e -> {
                Queue.addProfile(kit, profile);
                send(player, "&7You joined the queue for &e" + kit.getKitName() + "&7. &8(&7" + Queue.getPositionOf(profile) + "&8/&7" + Queue.getSize(kit) + "&8)");
                player.closeInventory();
            }));
        }
    }

    @Override
    public void update(Player player, InventoryContents contents) {
    }

}
