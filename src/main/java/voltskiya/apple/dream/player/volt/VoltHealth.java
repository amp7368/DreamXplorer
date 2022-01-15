package voltskiya.apple.dream.player.volt;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;

public class VoltHealth extends VoltComponent {
    private static final double BASE_HEALTH = 20;
    private double heartContainers = 0;

    @Override
    protected void initialize() {
        AttributeInstance maxHealth = getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (maxHealth != null) {
            maxHealth.setBaseValue(BASE_HEALTH + heartContainers);
        }
    }

    @Override
    protected void unload() {
    }
}
