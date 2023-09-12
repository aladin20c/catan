package Jade;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    private static Keyboard instance;
    private final boolean[] keyPressed = new boolean[1000];

    private Keyboard() {}

    public static Keyboard get() {
        if (Keyboard.instance == null) {
            Keyboard.instance = new Keyboard();
        }
        return Keyboard.instance;
    }

    public static boolean isKeyPressed(int keyCode) {
        return get().keyPressed[keyCode];
    }
    public static boolean isKeyPressed(KeyEvent e) {
        return get().keyPressed[e.getKeyCode()];
    }


    @Override
    public void keyTyped(KeyEvent e) {
        Window.getCurrentScene().keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()>=0 && e.getKeyCode()<=999) {
            get().keyPressed[e.getKeyCode()] = true;
        }
        Window.getCurrentScene().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()>=0 && e.getKeyCode()<=999) {
            get().keyPressed[e.getKeyCode()] = false;
        }
        Window.getCurrentScene().keyReleased(e);
    }
}
