package voltskiya.apple.dream.player.volt;

public class VoltHunger extends VoltComponent {
    private int hunger = 20;

    @Override
    protected void initialize() {
        getPlayer().setFoodLevel(hunger);
    }

    @Override
    protected void unload() {
        hunger = getPlayer().getFoodLevel();
    }
}
