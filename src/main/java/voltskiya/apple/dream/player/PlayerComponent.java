package voltskiya.apple.dream.player;

import org.bukkit.entity.Player;

public abstract class PlayerComponent<P> {
    protected transient P player;

    public void initialize_() {
        initialize();
    }

    protected abstract void initialize();

    public void unload_() {
        unload();
    }

    protected abstract void unload();

    protected abstract Player getPlayer();

    public void setPlayer(P player) {
        this.player = player;
    }
}
