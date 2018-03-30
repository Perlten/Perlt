/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import display.Camera;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import static tile.Tile.height;
import static tile.Tile.width;

/**
 *
 * @author Perlt
 */
public abstract class GameObject implements Serializable {

    protected int x, y;
    protected Rectangle collisionBox;
    protected boolean solid;
    protected String path;

    public GameObject(int x, int y, boolean solid, String path) {
        this.x = x;
        this.y = y;
        this.solid = solid;
        this.path = path;
    }

    public abstract void update();

    public abstract void render(Graphics g);

    protected void updateCollisionBox() {
        collisionBox.setBounds(x - Camera.xOffset, y, 32, 32);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    public boolean isSolid() {
        return solid;
    }

    public String getPath() {
        return path;
    }
}
