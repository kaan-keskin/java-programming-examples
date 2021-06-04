package snakegame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Snake {

    private static final int GameLoopMillis = 120;
    private static final int InputSleepMillis = GameLoopMillis / 10;

    private static void SafeSleep() {
        try {
            Thread.sleep(Snake.InputSleepMillis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       // Scanner vReader = new Scanner(System.in);

        List<int[]> playerPosition = new ArrayList<>();
        int[] nextPosition = {10, 10}; //Starting point
        int[] lastPosition;
        int[] coinPosition = randPosition(); //Random coin created
        playerPosition.add(nextPosition);

        int userInput, score = 0;
        boolean gameOver = false;
        String direction = "none";

        Grid grid = new Grid(20, 20, 600); //Game board grid matrix
        grid.setColor(coinPosition[1], coinPosition[0], Color.yellow);
        grid.setColor(nextPosition[1], nextPosition[0], Color.red);

        while (true) {

            int loopTimeMillis = 0;
            userInput = 0;
            while (userInput == 0 && loopTimeMillis < GameLoopMillis) {
                userInput = grid.getUserInput();
                SafeSleep();
                loopTimeMillis += InputSleepMillis;
            }

            switch (userInput) {
                case 87:
                case 38:
                    if (!direction.equals("down"))
                        direction = "up";
                    break;
                case 65:
                case 37:
                    if (!direction.equals("right"))
                        direction = "left";
                    break;
                case 83:
                case 40:
                    if (!direction.equals("up"))
                        direction = "down";
                    break;
                case 68:
                case 39:
                    if (!direction.equals("left"))
                        direction = "right";
                    break;
            }

            if (direction.equals("up"))
                nextPosition[1]--;
            else if (direction.equals("down"))
                nextPosition[1]++;
            else if (direction.equals("left"))
                nextPosition[0]--;
            else if (direction.equals("right"))
                nextPosition[0]++;

            playerPosition.add(nextPosition);
            if (samePosition(nextPosition, coinPosition)) {
                score++;
                grid.getMainFrame().setTitle("Snake Game! Score: " + score);
                while (inArray(playerPosition, coinPosition)) {
                    coinPosition = randPosition();
                }
                grid.setColor(coinPosition[1], coinPosition[0], Color.yellow);
            }

            if (playerPosition.size() > 3) {
                for (int i = 0; i < playerPosition.size() - 1; i++) {
                    if (samePosition(playerPosition.get(i), nextPosition))
                        gameOver = true;
                }
            }
            if (gameOver) {
                grid.setColor(nextPosition[1], nextPosition[0], Color.gray);
                break;
            }

            if (playerPosition.size() >= 2 * score + 1) {
                lastPosition = playerPosition.remove(0);
                grid.setColor(lastPosition[1], lastPosition[0], Color.white);
            }
            try {
                grid.setColor(nextPosition[1], nextPosition[0], Color.red);
            } catch (Exception e) {
                int square = playerPosition.size() - 2;
                try {
                    grid.setColor(playerPosition.get(square)[1], playerPosition.get(square)[0], Color.gray);
                } catch (Exception x) {
                    x.printStackTrace();
                }
                break;
            }

            nextPosition = new int[2];
            nextPosition[0] = playerPosition.get(playerPosition.size() - 1)[0];
            nextPosition[1] = playerPosition.get(playerPosition.size() - 1)[1];

        }
        System.out.println("Game Over. Your score : " + score);
      //  System.exit(0);

    }

    public static void printArray(List<int[]> arr) {
        for (int[] ints : arr) System.out.println(ints[1] + ", " + ints[0]);
        System.out.println();
    }

    private static boolean inArray(List<int[]> player, int[] next) {
        for (int x = 1; x < player.size(); x++) {
            if (samePosition(player.get(x), next)) {
                return true;
            }
        }
        return false;
    }

    private static boolean samePosition(int[] play, int[] thing) {
        return play[0] == thing[0] && play[1] == thing[1];
    }

    private static int[] randPosition() {
        Random rand = new Random();
        return new int[]{rand.nextInt(20), rand.nextInt(20)};
    }
}
