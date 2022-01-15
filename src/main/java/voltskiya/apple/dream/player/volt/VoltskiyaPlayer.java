package voltskiya.apple.dream.player.volt;

import apple.utilities.database.SaveFileable;
import apple.utilities.database.ajd.AppleAJDTypedImpl;
import apple.utilities.util.FileFormatting;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import plugin.util.plugin.plugin.util.plugin.FileIOServiceNow;
import voltskiya.apple.dream.player.PluginPlayer;
import voltskiya.apple.dream.player.inventory.InventoryManager;
import voltskiya.apple.utilities.util.minecraft.inventory.InventorySerializable;

import java.io.File;
import java.util.UUID;
import java.util.function.Consumer;

public class VoltskiyaPlayer implements SaveFileable {
    private static final File folder = PluginPlayer.get().folderWithChildrenI(PluginPlayer.get().getDataFolder(), "Voltskiya");
    private static AppleAJDTypedImpl<VoltskiyaPlayer> manager;
    private final VoltHealth health = new VoltHealth();
    private final VoltHunger hunger = new VoltHunger();
    private transient Player player;
    private UUID uuid;
    private InventorySerializable inventory = new InventorySerializable();

    public VoltskiyaPlayer(@NotNull Player player) {
        this.uuid = player.getUniqueId();
        this.player = player;
    }

    public VoltskiyaPlayer() {
    }

    public static void load() {
        manager = new AppleAJDTypedImpl<>(getDataFolder(), VoltskiyaPlayer.class, FileIOServiceNow.get());
    }

    public static void lookupPlayer(UUID uuid, Consumer<VoltskiyaPlayer> runAfter) {
        manager.load(getFile(uuid), runAfter);
    }

    private static File getFile(UUID uuid) {
        return new File(getDataFolder(), FileFormatting.extensionJson(uuid.toString()));
    }

    private static File getDataFolder() {
        return folder;
    }

    public void initialize() {
        InventoryManager.setInventory(player, getInventory());
        health.initialize_();
        hunger.initialize_();
    }

    @Override
    public String getSaveFileName() {
        return FileFormatting.extensionJson(uuid.toString());
    }

    public InventorySerializable getInventory() {
        return this.inventory;
    }

    public void setInventory(InventorySerializable inventory) {
        this.inventory = inventory;
    }

    public void save() {
        manager.save(this);
    }

    public void unload() {
        this.inventory = InventoryManager.makeInventory(player.getInventory());
        this.health.unload_();
        this.hunger.unload_();
        save();
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        health.setPlayer(this);
        hunger.setPlayer(this);
    }
}

