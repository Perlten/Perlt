package keyinput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Perlt
 */
public class KeyInput implements KeyListener{
    
    private boolean[] keysPressed;
    
    private boolean up, down, left, right;

    public KeyInput() {
        this.keysPressed = new boolean[256];
    }
    
    public void update(){
        up = keysPressed[KeyEvent.VK_W];
        down = keysPressed[KeyEvent.VK_S];
        left = keysPressed[KeyEvent.VK_A];
        right = keysPressed[KeyEvent.VK_D];
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        keysPressed[ke.getKeyCode()] = true;
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
}
