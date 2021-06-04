package ponggame.gameobjects;

import ponggame.GameCanvas;

import java.awt.event.KeyEvent;

public class PlayerBattler extends Battler {

    private double customSpeed;

    public PlayerBattler(GameCanvas canvas, Side side, double customSpeed) {
        super(canvas, side);
        this.customSpeed = customSpeed;
    }

    public PlayerBattler(GameCanvas canvas, Side side) {
        this(canvas, side, Y_SPEED);
    }

    @Override
    public void update(double elapsed) {
        double sign = 0;
        if (getSide() == Side.LEFT) {
            if (getCanvas().getKeyRegistry().isTyped(KeyEvent.VK_W)) {
                sign = -1;
            }
            if (getCanvas().getKeyRegistry().isTyped(KeyEvent.VK_S)) {
                sign = 1;
            }
        } else {
            if (getCanvas().getKeyRegistry().isTyped(KeyEvent.VK_UP)) {
                sign = -1;
            }
            if (getCanvas().getKeyRegistry().isTyped(KeyEvent.VK_DOWN)) {
                sign = 1;
            }
        }
        if (sign != 0) {
            addY(sign * Y_SPEED * elapsed);
        }
    }

}
