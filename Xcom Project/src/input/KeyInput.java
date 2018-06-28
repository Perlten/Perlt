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
    private boolean u, i, n, l;
    private boolean[] keysPressed = new boolean[256];
    private boolean[] keysReleased = new boolean[256];

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keysPressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed[e.getKeyCode()] = false;
        keysReleased[e.getKeyCode()] = true;
        if (e.getKeyCode() == KeyEvent.VK_U) {
            u = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_I) {
            i = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_N){
            n = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_L){
            l = true;
        }
    }

    public void update() {
        up = keysPressed[KeyEvent.VK_W];
        down = keysPressed[KeyEvent.VK_S];
        left = keysPressed[KeyEvent.VK_A];
        right = keysPressed[KeyEvent.VK_D];
        space = keysPressed[KeyEvent.VK_SPACE];

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

    public boolean isU() {
        boolean temp = u;
        u = false;
        return temp;
    }

    public boolean isI() {
        boolean temp = i;
        i = false;
        return temp;
    }

    public boolean isN(){
        boolean temp = n;
        n = false;
        return temp;
    }

    public boolean isL() {
        boolean temp = l;
        l = false;
        return temp;
    }
    
    
    public int lastestNumKey() {
        if (keysReleased[KeyEvent.VK_0]) {
            keysReleased[KeyEvent.VK_0] = false;
            return 0;
        }
        if (keysReleased[KeyEvent.VK_1]) {
            keysReleased[KeyEvent.VK_1] = false;
            return 1;
        }
        if (keysReleased[KeyEvent.VK_2]) {
            keysReleased[KeyEvent.VK_2] = false;
            return 2;
        }
        if (keysReleased[KeyEvent.VK_3]) {
            keysReleased[KeyEvent.VK_3] = false;
            return 3;
        }
        if (keysReleased[KeyEvent.VK_4]) {
            keysReleased[KeyEvent.VK_4] = false;
            return 4;
        }
        return -1;
    }

}
