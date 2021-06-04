package ponggame.gameobjects;

import ponggame.GameCanvas;
import ponggame.TwoDimension;
import ponggame.interfaces.Updateable;

import java.awt.*;

public abstract class GameObject implements Updateable {

    private GameCanvas canvas;
    private TwoDimension location;

    public GameObject(GameCanvas canvas) {
        this.canvas = canvas;
        location = new TwoDimension();
    }

    public abstract void paint(Graphics g);

    public TwoDimension getLocation() {
        return location;
    }

    public void setLocation(TwoDimension location) {
        this.location = location;
    }

    public GameCanvas getCanvas() {
        return canvas;
    }
}
