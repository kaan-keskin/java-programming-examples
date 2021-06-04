package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Grid {
    private final JPanel[][] grid;
    private int userInput;

    private JFrame mainFrame;
    public JFrame getMainFrame() {
        return mainFrame;
    }

    public Grid(int x, int y, int size) {
        grid = new JPanel[x][y];
        mainFrame = new JFrame();
        mainFrame.setTitle("Snake Game!");
        mainFrame.setSize(size, size);
        Container pane = mainFrame.getContentPane();

        pane.setLayout(new GridLayout(x, y));
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[j][i] = new JPanel();
                grid[j][i].setBackground(Color.white);
                grid[j][i].setBorder(BorderFactory.createLineBorder(Color.black));
                pane.add(grid[j][i]);
            }
        }

        mainFrame.setVisible(true);
        mainFrame.setFocusable(true);
        mainFrame.requestFocusInWindow();

        mainFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                userInput = e.getKeyCode();
            }
        });

    }

    public void setColor(int x, int y, Color c) {
        grid[y][x].setBackground(c);
    }

    public int getUserInput() {
        int code = userInput;
        userInput = 0;
        return code;
    }
}