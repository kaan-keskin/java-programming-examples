package ponggame;

import ponggame.gameobjects.Ball;
import ponggame.gameobjects.Battler;
import ponggame.gameobjects.IntelligentBattler;
import ponggame.gameobjects.PlayerBattler;
import ponggame.interfaces.Updateable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameCanvas extends Canvas implements Updateable, KeyListener {

    private static final long serialVersionUID = 569910694522235826L;

    private int leftPoints, rightPoints;
    private KeyRegistry keyRegistry;
    private GameFrame frame;
    private Ball ball;
    private Battler leftBattler, rightBattler;

    public GameCanvas(GameFrame frame) {
        this.frame = frame;
        this.setSize(frame.getSize());

        this.keyRegistry = new KeyRegistry();

        initBattlers();

        ball = new Ball(this);
        ball.setLocation(new TwoDimension(getWidth() / 2, getHeight() / 2));
        ball.randomVelocity();

        addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();

        frame.getGameThread().add(this);

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());

        ball.paint(g);
        leftBattler.paint(g);
        rightBattler.paint(g);
        drawBattlerPoints(g);
        drawCredits(g);

        frame.validate();
        frame.repaint();
    }

    private void drawBattlerPoints(Graphics g) {
        g.setFont(new Font("Calibri", Font.BOLD, 24));
        g.drawString(String.valueOf(leftPoints), getWidth() / 2 - 50, 30);
        g.drawString(String.valueOf(rightPoints), getWidth() / 2 + 40, 30);
    }

    private void drawCredits(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.BOLD, 16));
        g.drawString("Designed by Kaan Keskin with love!", 20, getHeight() - 20);
        g.drawString("Play with arrows for right battler.", 20, getHeight() - 50);
    }

    private void initBattlers() {
        leftBattler = new IntelligentBattler(this, Battler.Side.LEFT, 260);
        //	rightBattler = new IntelligentBattler(this, Battler.Side.RIGHT, 260);
        rightBattler = new PlayerBattler(this, Battler.Side.RIGHT, 260);
        leftBattler.setLocation(new TwoDimension(5, getHeight() / 2 - leftBattler.getSize().getHeight() / 2));
        rightBattler.setLocation(new TwoDimension(getWidth() - rightBattler.getSize().getWidth() - rightBattler.getSize().getWidth(),
                leftBattler.getSize().getHeight() / 2));
    }

    @Override
    public void update(double elapsed) {
        requestFocus();
        leftBattler.update(elapsed);
        rightBattler.update(elapsed);
        ball.update(elapsed);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Nothing because it does not interact with arrow keys
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode() + " PRESSED");
        keyRegistry.setTyped(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyCode() + " RELEASED");
        keyRegistry.setTyped(e.getKeyCode(), false);
    }

    public KeyRegistry getKeyRegistry() {
        return keyRegistry;
    }

    public Battler getLeftBattler() {
        return leftBattler;
    }

    public Battler getRightBattler() {
        return rightBattler;
    }

    public Ball getBall() {
        return ball;
    }

    public void ballIsOutside() {
        if (ball.getLocation().getX() < getWidth() / 2) {
            // it is on the left side
            rightPoints++;
        } else {
            //it is on the right side
            leftPoints++;
        }
        ball.setLocation(new TwoDimension(getWidth() / 2, getHeight() / 2));
        ball.randomVelocity();
    }

}
