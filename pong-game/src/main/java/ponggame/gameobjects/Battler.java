package ponggame.gameobjects;

import ponggame.GameCanvas;
import ponggame.TwoDimension;

import java.awt.*;

public abstract class Battler extends GameObject {

    public static final double Y_SPEED = 240D;
    private Side side;
    private Dimension size = new Dimension(20, 100);

    public Battler(GameCanvas canvas, Side side) {
        super(canvas);
        this.side = side;
    }

    @Override
    public void paint(Graphics g) {
        Dimension dimension = getSize();
        TwoDimension position = getLocation();
        g.setColor(Color.WHITE);
        g.fillRect(
                (int) position.getX(), (int) position.getY(),
                (int) dimension.getWidth(), (int) dimension.getHeight()
        );
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public void addY(double y) {
        getLocation().add(0, y);
    }

    public boolean intersectsWith(Ball ball) {
        Dimension size = getSize();
        TwoDimension battlerPosition = getLocation();
        TwoDimension ballPosition = ball.getLocation();
        // simplify the ball into a point
        ballPosition = new TwoDimension(ballPosition.getX() + ball.getRadius() / 2, ballPosition.getY() + ball.getRadius() / 2);
        // Check that ball is inside the width and height of the battler
        return battlerPosition.getX() <= ballPosition.getX()
                && ballPosition.getX() <= battlerPosition.getX() + size.getWidth()
                && battlerPosition.getY() <= ballPosition.getY()
                && ballPosition.getY() < battlerPosition.getY() + size.getHeight();
    }

    public Side getSide() {
        return side;
    }

    public enum Side {
        LEFT,
        RIGHT
    }

}
