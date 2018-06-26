/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Perlt
 */
public class KeyInput implements KeyListener {

    private boolean up, down, left, right, space;
    private boolean[] keysTyped = new boolean[256];

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keysTyped[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysTyped[e.getKeyCode()] = false;
    }

    public void update() {
        up = keysTyped[KeyEvent.VK_W];
        down = keysTyped[KeyEvent.VK_S];
        left = keysTyped[KeyEvent.VK_A];
        right = keysTyped[KeyEvent.VK_D];
        space = keysTyped[KeyEvent.VK_SPACE];
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isSpace() {
        return space;
    }
}
