package ponggame;

public class TwoDimension {

    private double x, y;

    public TwoDimension() {
    }

    public TwoDimension(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }
}
