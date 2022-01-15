package voltskiya.apple.dream.base;

import plugin.util.plugin.plugin.util.plugin.PluginManagedModule;

public class PluginDamage extends PluginManagedModule {
    @Override
    public void enable() {
        new MainDamageListener();
    }

    @Override
    public String getName() {
        return "CustomHp";
    }
}
