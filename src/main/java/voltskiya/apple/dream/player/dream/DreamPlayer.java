package voltskiya.apple.dream.player.dream;

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

public class DreamPlayer implements SaveFileable {
    private static final File folder = PluginPlayer.get().folderWithChildrenI(PluginPlayer.get().getDataFolder(), "dream");
    private static AppleAJDTypedImpl<DreamPlayer> manager;

    private transient Player player;

    private UUID uuid;
    private InventorySerializable inventory = new InventorySerializable();
    private DreamHealth dreamHealth = new DreamHealth();
    private DreamHunger dreamHunger = new DreamHunger();

    public DreamPlayer(@NotNull Player player) {
        this.uuid = player.getUniqueId();
        this.player = player;
    }

    public DreamPlayer() {
    }

    public static void load() {
        manager = new AppleAJDTypedImpl<>(getDataFolder(), DreamPlayer.class, FileIOServiceNow.get());
    }

    public static void lookupPlayer(UUID uuid, Consumer<DreamPlayer> runAfter) {
        manager.load(getFile(uuid), runAfter);
    }

    private static File getFile(UUID uuid) {
        return new File(getDataFolder(), FileFormatting.extensionJson(uuid.toString()));
    }

    private static File getDataFolder() {
        return folder;
    }

    public void initialize() {
        dreamHealth.initialize_();
        dreamHunger.initialize_();
        InventoryManager.setInventory(player, getInventory());
    }

    @Override
    public String getSaveFileName() {
        return FileFormatting.extensionJson(uuid.toString());
    }

    public InventorySerializable getInventory() {
        return this.inventory;
    }

    public void save() {
        manager.save(this);
    }

    public void unload() {
        this.inventory = InventoryManager.makeInventory(player.getInventory());
        this.dreamHealth.unload_();
        this.dreamHunger.unload_();
        save();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        dreamHealth.setPlayer(this);
        dreamHunger.setPlayer(this);
        this.player = player;
    }
}
