package ponggame.gameobjects;

import ponggame.GameCanvas;
import ponggame.TwoDimension;

import java.awt.*;
import java.util.Random;

public class Ball extends GameObject {

    private final int MIN_X_SPEED = 120, MAX_X_SPEED = 240, MIN_Y_SPEED = 200, MAX_Y_SPEED = 400;
    private double vX = 120, vY = 360;
    private double radius = 20;

    public Ball(GameCanvas canvas) {
        super(canvas);
    }

    public double getvX() {
        return vX;
    }

    public void setvX(double vX) {
        this.vX = vX;
    }

    public double getvY() {
        return vY;
    }

    public void setvY(double vY) {
        this.vY = vY;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void paint(Graphics g) {
        TwoDimension position = getLocation();
        g.setColor(Color.WHITE);
        g.fillOval(
                (int) position.getX(), (int) position.getY(),
                (int) getRadius(), (int) getRadius()
        );
    }

    @Override
    public void update(double elapsed) {
        TwoDimension position = getLocation();
        double newY = position.getY() + getvY() * elapsed;
        double newX = position.getX() + getvX() * elapsed;
        if (newY < 0) {
            newY = getRadius() / 2;
            setvY(-getvY());
        } else if (newY > getCanvas().getHeight()) {
            setvY(-getvY());
            newY = getCanvas().getHeight() - Math.ceil(getRadius() / 2);
        }

        boolean ballOutside = false;
        if (newX <= 0) {
            ballOutside = true;
        } else if (newX >= getCanvas().getWidth() - getRadius()) {
            ballOutside = true;
        }
        if (ballOutside) {
            getCanvas().ballIsOutside();
        } else {
            // if it is going to the left side
            if (getvX() < 0) {
                if (getCanvas().getLeftBattler().intersectsWith(this)) {
                    setvX(-getvX());
                }
            } else {
                // definitely going to the right side
                if (getCanvas().getRightBattler().intersectsWith(this)) {
                    setvX(-getvX());
                }
            }
        }

        position.setX(newX);
        position.setY(newY);
    }

    public void randomVelocity() {
        Random rand = new Random();
        setvX((rand.nextInt(MAX_X_SPEED - MIN_X_SPEED) + MIN_X_SPEED) * (Math.random() < 0.5 ? 1 : -1));
        setvY((rand.nextInt(MAX_Y_SPEED - MIN_Y_SPEED) + MIN_Y_SPEED) * (Math.random() < 0.5 ? 1 : -1));
    }

}
