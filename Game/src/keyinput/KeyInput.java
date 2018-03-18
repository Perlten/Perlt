package keyinput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private boolean[] keysPressed;

    private boolean up, down, left, right, debug, test;

    public KeyInput() {
        this.keysPressed = new boolean[256];
    }

    public void update() {
        up = keysPressed[KeyEvent.VK_W];
        down = keysPressed[KeyEvent.VK_S];
        left = keysPressed[KeyEvent.VK_A];
        right = keysPressed[KeyEvent.VK_D];
        test = keysPressed[KeyEvent.VK_T];
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
         if (ke.getKeyCode() == KeyEvent.VK_U) {
             debug = !debug;
         }else{
        keysPressed[ke.getKeyCode()] = true;
         }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
            keysPressed[ke.getKeyCode()] = false;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isTest() {
        return test;
    }
}
