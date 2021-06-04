package ponggame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyRegistry {

    private final Object LOCK = new Object();
    private Map<Integer, Boolean> keyCodes;

    public KeyRegistry() {
        keyCodes = new HashMap<>();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                synchronized (LOCK) {
                    if (e.getID() == KeyEvent.KEY_PRESSED) {
                        setTyped(e.getKeyCode(), true);
                    } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                        setTyped(e.getKeyCode(), false);
                    }
                }
                return false;
            }
        });
    }

    public void setTyped(int keyCode, boolean typed) {
        keyCodes.put(keyCode, typed);
    }

    public boolean isTyped(int keyCode) {
        Boolean typed = keyCodes.get(keyCode);
        if (typed != null) {
            return typed;
        }
        return false;
    }

}
