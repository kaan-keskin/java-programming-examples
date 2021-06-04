package ponggame;

import ponggame.interfaces.Updateable;

import java.util.ArrayList;
import java.util.List;

public class GameThread extends Thread {

    private final Object LOCK = new Object();
    private List<Updateable> toUpdate;
    private long lastTime;
    private boolean isRunnable = true;
    private long updateRecurrence = 25; // in ms

    public GameThread() {
        toUpdate = new ArrayList<>();
    }

    public void run() {
        while (isRunnable) {
            long now = System.currentTimeMillis();
            double elapsed = ((double) (now - lastTime)) / 1000D;
            synchronized (LOCK) {
                toUpdate.forEach(updateable -> updateable.update(elapsed));
            }
            lastTime = now;
            try {
                Thread.sleep(updateRecurrence);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void add(Updateable up) {
        synchronized (LOCK) {
            toUpdate.add(up);
        }
    }

    public void remove(Updateable up) {
        synchronized (LOCK) {
            toUpdate.remove(up);
        }
    }

}
