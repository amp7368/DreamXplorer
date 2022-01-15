package voltskiya.apple.dream.player;

import plugin.util.plugin.plugin.util.plugin.PluginManagedModule;
import voltskiya.apple.configs.plugin.manage.ConfigBuilderHolder;
import voltskiya.apple.configs.plugin.manage.ConfigsVoltskiyaManager;
import voltskiya.apple.configs.plugin.manage.PluginManagedModuleConfig;
import voltskiya.apple.dream.player.dream.DreamPlayer;
import voltskiya.apple.dream.player.volt.VoltskiyaPlayer;
import voltskiya.apple.dream.player.watch.PlayerWatcher;
import voltskiya.apple.dream.player.watch.WatchPlayerManager;

import java.util.Collection;
import java.util.List;

public class PluginPlayer extends PluginManagedModule implements PluginManagedModuleConfig {
    private static PluginPlayer instance;
    private String playerConfigGeneral = "PlayerConfigGeneral";

    public PluginPlayer() {
        instance = this;
    }

    public static PluginPlayer get() {
        return instance;
    }

    @Override
    public void enable() {
        DreamPlayer.load();
        VoltskiyaPlayer.load();
        new WatchPlayerManager();
        new PlayerWatcher();
    }

    @Override
    public String getName() {
        return "Player";
    }

    @Override
    public Collection<ConfigBuilderHolder<?>> getConfigsToRegister() {
        return List.of(
                configFolder(
                        gson(PlayerConfigGeneral.class).setName(playerConfigGeneral)
                ).nameAsExtension().setExtension(this::extensionJsonI)
        );
    }

    public void savePlayerConfigGeneral() {
        ConfigsVoltskiyaManager.get().getConfig(playerConfigGeneral).saveQueue();
    }
}
