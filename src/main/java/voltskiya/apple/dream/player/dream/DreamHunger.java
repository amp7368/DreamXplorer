package voltskiya.apple.dream.player.dream;

public class DreamHunger extends DreamComponent {
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
