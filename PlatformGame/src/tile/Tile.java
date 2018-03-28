/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;

import display.Camera;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import util.Util;

/**
 *
 * @author Perlt
 */
public abstract class Tile implements Serializable {

    //Size of the tiles
    public static final int width = 32;
    public static final int height = 32;
    
    protected int x, y;
    protected String path;
    protected transient BufferedImage texture;
    protected Rectangle collisionBox;
    protected int id;
    
    protected boolean solid = false;

    public Tile(int x, int y, String path, int id, boolean solid) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.path = path;
        this.solid = solid;
        this.collisionBox = new Rectangle(width, height);
    }

    public abstract void update();
    public abstract void render(Graphics g);

    protected void updateCollisionBox(){
        collisionBox.setBounds(x - Camera.xOffset, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getId() {
        return id;
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
