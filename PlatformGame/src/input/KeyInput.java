package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import mapEditor.GameObjectManager;

public class KeyInput implements KeyListener {

    private boolean[] keys;
    private boolean up, down, left, right, editor, save, delete, removeLast;
    private int tileId = 1;
    private boolean nextLevel;
    
    public KeyInput() {
        keys = new boolean[256];
    }

    public void update() {
        up = keys[KeyEvent.VK_SPACE];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        delete = keys[KeyEvent.VK_L];
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        keys[ke.getKeyCode()] = true;
        try {
            for (int i = 1; i <= GameObjectManager.TotalTiles; i++) {
                if (Integer.parseInt(String.valueOf(ke.getKeyChar())) == i) {
                    tileId = i;
                }
            }
        } catch (NumberFormatException e) {
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        keys[ke.getKeyCode()] = false;
        if (ke.getKeyCode() == KeyEvent.VK_U) {
            editor = !editor;
        }
        if (ke.getKeyCode() == KeyEvent.VK_N) {
            save = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_Z) {
            removeLast = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_B) {
            nextLevel = true;
        }
        

    }

    public boolean[] getKeys() {
        return keys;
    }

    public boolean isEditor() {
        return editor;
    }

    public int getTileId() {
        return tileId;
    }

    public boolean isSave() {
        return save;
    }

    public void setSaveFalse() {
        this.save = false;
    }
    
    public void setNextLevelFalse(){
        nextLevel = false;
    }

    public void setRemoveLastFalse() {
        removeLast = false;
    }

    public boolean isUp() {
        return up;
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

    public boolean isDelete() {
        return delete;
    }

    public boolean isRemoveLast() {
        return removeLast;
    }

    public boolean isNextLevel() {
        return nextLevel;
    }
    
}
