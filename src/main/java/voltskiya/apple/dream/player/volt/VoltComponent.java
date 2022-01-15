package voltskiya.apple.dream.player.volt;

import org.bukkit.entity.Player;
import voltskiya.apple.dream.player.PlayerComponent;

public abstract class VoltComponent extends PlayerComponent<VoltskiyaPlayer> {
    @Override
    protected Player getPlayer() {
        return player.getPlayer();
    }
}
