package voltskiya.apple.dream.player.dream;

import org.bukkit.entity.Player;
import voltskiya.apple.dream.player.PlayerComponent;

public abstract class DreamComponent extends PlayerComponent<DreamPlayer> {
    @Override
    protected Player getPlayer() {
        return player.getPlayer();
    }
}
