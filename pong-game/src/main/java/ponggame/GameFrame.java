package ponggame;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private static final long serialVersionUID = 5701303869766487530L;

    private GameCanvas canvas;

    private GameThread gameThread;
    private double width;
    private double height;
    public GameFrame() {

        super("Pong Game");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth() / 2;
        height = screenSize.getHeight() / 2;
        setSize((int) width, (int) height);

        (gameThread = new GameThread()).start();

        canvas = new GameCanvas(this);
        add(canvas);

        //Center the frame onto the screen
        setLocationRelativeTo(null);

        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public GameThread getGameThread() {
        return gameThread;
    }

    public int getWidth() {
        return (int) width;
    }

    public int getHeight() {
        return (int) height;
    }

}
