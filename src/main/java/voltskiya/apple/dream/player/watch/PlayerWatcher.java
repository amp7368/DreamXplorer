package voltskiya.apple.dream.player.watch;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import voltskiya.apple.dream.VoltskiyaPlugin;
import voltskiya.apple.dream.player.PlayerConfigGeneral;
import voltskiya.apple.dream.player.dream.DreamPlayer;
import voltskiya.apple.dream.player.volt.VoltskiyaPlayer;

import java.util.UUID;

public class PlayerWatcher implements Listener {
    public PlayerWatcher() {
        VoltskiyaPlugin.get().registerEvents(this);
    }

    @EventHandler
    public void playerJoin(PlayerChangedWorldEvent event) {
        UUID fromUID = event.getFrom().getUID();
        Player player = event.getPlayer();

        WatchPlayerManager.lookupPlayer(player, (voltPlayer, dreamPlayer) -> {
            VoltskiyaPlugin.get().scheduleSyncDelayedTask(() -> {
                scheduleChangeWorld(player, fromUID, dreamPlayer, voltPlayer);
            });
        });
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void playerTeleport(PlayerTeleportEvent event) {
        UUID fromUID = event.getFrom().getWorld().getUID();
        if (!fromUID.equals(event.getTo().getWorld().getUID())) {
            Player player = event.getPlayer();

            WatchPlayerManager.lookupPlayer(player, (voltPlayer, dreamPlayer) -> {
                VoltskiyaPlugin.get().scheduleSyncDelayedTask(() -> {
                    scheduleChangeWorld(player, fromUID, dreamPlayer, voltPlayer);
                });
            });
        }
    }

    private void scheduleChangeWorld(Player player, UUID fromUID, DreamPlayer dreamPlayer, VoltskiyaPlayer voltPlayer) {
        if (player.getWorld().getUID().equals(PlayerConfigGeneral.get().dreamWorld)) {
            WatchPlayerManager.get().toDream(player, voltPlayer, dreamPlayer);
        } else if (fromUID.equals(PlayerConfigGeneral.get().dreamWorld)) {
            WatchPlayerManager.get().toVolt(player, voltPlayer, dreamPlayer);
        }
    }
}
