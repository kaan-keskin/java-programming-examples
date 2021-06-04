package ponggame.gameobjects;

import ponggame.GameCanvas;
import ponggame.TwoDimension;

public class IntelligentBattler extends Battler {

    private double customSpeed;

    public IntelligentBattler(GameCanvas canvas, Side side, double customSpeed) {
        super(canvas, side);
        this.customSpeed = customSpeed;
    }

    public IntelligentBattler(GameCanvas canvas, Side side) {
        this(canvas, side, Y_SPEED);
    }

    @Override
    public void update(double elapsed) {
        if (getSide() == Side.LEFT) {
            if (getCanvas().getBall().getvX() < 0)
                goToBall(elapsed);
        } else {
            if (getCanvas().getBall().getvX() > 0) {
                goToBall(elapsed);
            }
        }
    }

    private void goToBall(double elapsed) {
        TwoDimension ballPosition = getCanvas().getBall().getLocation();
        TwoDimension position = getLocation();
        ballPosition = new TwoDimension(
                ballPosition.getX() + getCanvas().getBall().getRadius() / 2,
                ballPosition.getY() + getCanvas().getBall().getRadius() / 2
        );
        position = new TwoDimension(
                position.getX() + getSize().getWidth() / 2,
                position.getY() + getSize().getHeight() / 2
        );
        double direction = ballPosition.getY() - position.getY();
        if (direction != 0) {
            direction = Math.abs(direction) / direction;
        }
        addY(direction * elapsed * customSpeed);
    }

}
