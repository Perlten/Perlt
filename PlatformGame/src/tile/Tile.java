/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;

import display.Camera;
import game.GameObject;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import util.Util;

/**
 *
 * @author Perlt
 */
public abstract class Tile extends GameObject {

    //Size of the tiles
    public static final int width = 32;
    public static final int height = 32;
    
    protected String path;
    protected transient BufferedImage texture;
    protected int id;

    public static boolean editor;

    public Tile(int x, int y, String path, int id, boolean solid) {
        super(x, y, solid);
        this.id = id;
        this.path = path;
        collisionBox = new Rectangle(width, height);
    }

    public abstract void update();
    public abstract void render(Graphics g);

    protected void updateCollisionBox(){
        collisionBox.setBounds(x - Camera.xOffset, y, width, height);
    }

    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    public boolean isSolid() {
        return solid;
    }

}
