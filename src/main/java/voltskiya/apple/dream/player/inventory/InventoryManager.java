package voltskiya.apple.dream.player.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import voltskiya.apple.utilities.util.minecraft.inventory.InventorySerializable;

public class InventoryManager {
    public static InventorySerializable makeInventory(PlayerInventory inventory) {
        return new InventorySerializable(inventory);
    }

    public static void setInventory(InventorySerializable inventory) {

    }

    public static void setInventory(Player player, InventorySerializable inventory) {
        player.getInventory().setContents(inventory.toContents());
    }
}
