/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;

import game.GameObject;
import java.awt.image.BufferedImage;

/**
 *
 * @author Perlt
 */
public abstract class Tile extends GameObject {

    //Size of the tiles
    public static final int width = 32;
    public static final int height = 32;
    
    protected transient BufferedImage texture;
    protected int id;

    public static boolean editor;

    public Tile(int x, int y, String path, int id, boolean solid) {
        super(x, y, solid, path);
        this.id = id;
        this.path = path;
    }

    public boolean isSolid() {
        return solid;
    }

}
