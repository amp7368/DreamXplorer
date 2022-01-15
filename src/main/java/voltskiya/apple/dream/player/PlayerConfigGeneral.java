package voltskiya.apple.dream.player;

import ycm.yml.manager.fields.YcmField;

import java.util.UUID;

public class PlayerConfigGeneral {
    private static PlayerConfigGeneral instance;
    @YcmField
    public UUID dreamWorld = UUID.randomUUID();

    public PlayerConfigGeneral() {
        instance = this;
    }

    public static PlayerConfigGeneral get() {
        return instance;
    }

    public void save() {
        PluginPlayer.get().savePlayerConfigGeneral();
    }
}
