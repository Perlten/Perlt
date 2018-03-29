/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;

import display.Camera;
import java.awt.Graphics;
import util.Util;

/**
 *
 * @author Perlt
 */
public class StoneTile extends Tile {

    public StoneTile(int x, int y, String path, int id) {
        super(x, y, path, id, true);
    }

//    public StoneTile(int x, int y, String path, int id) {
//        super(x, y, path, id, true);
//    }
    

    @Override
    public void update() {
        updateCollisionBox();
    }

    @Override
    public void render(Graphics g) {
        if(texture == null){
            texture = Util.getImage(path);
        }
        g.drawImage(Util.getImage(path), x - Camera.xOffset, y, null);
//        g.fillRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height); // draws collision box
    }
}
