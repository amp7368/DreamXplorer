package voltskiya.apple.dream.player.watch;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import voltskiya.apple.dream.player.dream.DreamPlayer;
import voltskiya.apple.dream.player.volt.VoltskiyaPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;

public class WatchPlayerManager {
    private static WatchPlayerManager instance;
    private final Map<UUID, DreamPlayer> dreamPlayers = new HashMap<>();
    private final Map<UUID, VoltskiyaPlayer> voltPlayers = new HashMap<>();
    private final Object sync = new Object();

    public WatchPlayerManager() {
        instance = this;
        for (Player player : Bukkit.getOnlinePlayers()) {
            lookupPlayer(player, (voltPlayer, dreamPlayer) -> {
                toDream(player, voltPlayer, dreamPlayer);
            });
        }
    }

    public static WatchPlayerManager get() {
        return instance;
    }

    public static void lookupPlayer(Player player, BiConsumer<VoltskiyaPlayer, DreamPlayer> callback) {
        VoltskiyaPlayer.lookupPlayer(player.getUniqueId(),
                (voltPlayer) -> DreamPlayer.lookupPlayer(player.getUniqueId(),
                        (dreamPlayer) -> lookupPlayer(player, callback, voltPlayer, dreamPlayer)
                )
        );
    }

    private static void lookupPlayer(Player player, BiConsumer<VoltskiyaPlayer, DreamPlayer> callback, VoltskiyaPlayer voltPlayer, DreamPlayer dreamPlayer) {
        if (dreamPlayer == null) dreamPlayer = new DreamPlayer(player);
        if (voltPlayer == null) voltPlayer = new VoltskiyaPlayer(player);
        voltPlayer.setPlayer(player);
        dreamPlayer.setPlayer(player);
        callback.accept(voltPlayer, dreamPlayer);
    }

    public void toDream(Player player, VoltskiyaPlayer voltPlayer, DreamPlayer dreamPlayer) {
        voltPlayer.unload();
        dreamPlayer.initialize();
        synchronized (sync) {
            dreamPlayers.put(player.getUniqueId(), dreamPlayer);
            voltPlayers.remove(player.getUniqueId());
        }
    }

    public void toVolt(Player player, VoltskiyaPlayer voltPlayer, DreamPlayer dreamPlayer) {
        dreamPlayer.unload();
        voltPlayer.initialize();
        synchronized (sync) {
            dreamPlayers.remove(player.getUniqueId());
            voltPlayers.put(player.getUniqueId(), voltPlayer);
        }
    }

    public void leave(Player player) {
        synchronized (sync) {
            dreamPlayers.remove(player.getUniqueId());
            voltPlayers.remove(player.getUniqueId());
        }
    }
}
