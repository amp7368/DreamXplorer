package voltskiya.apple.dream;

import plugin.util.plugin.plugin.util.plugin.PluginManaged;
import plugin.util.plugin.plugin.util.plugin.PluginManagedModule;
import voltskiya.apple.configs.plugin.manage.PluginManagedConfigRegister;
import voltskiya.apple.dream.base.PluginDamage;
import voltskiya.apple.dream.player.PluginPlayer;

import java.util.Collection;
import java.util.List;

public class VoltskiyaPlugin extends PluginManaged implements PluginManagedConfigRegister {
    private static VoltskiyaPlugin instance;

    public VoltskiyaPlugin() {
        instance = this;
    }

    public static VoltskiyaPlugin get() {
        return instance;
    }

    @Override
    public void initialize() {
        registerAllConfigs();
        new CommandDream();
    }

    @Override
    public Collection<PluginManagedModule> getModules() {
        return List.of(
                new PluginDamage(),
                new PluginPlayer()
        );
    }
}
