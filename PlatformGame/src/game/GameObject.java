/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 *
 * @author Perlt
 */
public abstract class GameObject implements Serializable{
        
    protected int x, y;
    protected Rectangle collisionBox;
    protected boolean solid;

    public GameObject(int x, int y, boolean solid) {
        this.x = x;
        this.y = y;
        this.solid = solid;
    }
    
    public abstract void update();
    public abstract void render(Graphics g);

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
}
