/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import display.Camera;
import display.FpsManager;
import java.awt.Graphics;
import java.awt.Rectangle;
import util.Util;

/**
 *
 * @author Perlt
 */
public class WinHeart extends Entity {

    public WinHeart(int x, int y) {
        super(x, y, "resources/textures/blueHeart.png", 0, new Rectangle());
    }

    @Override
    public void update() {
        if (animationLock == null) {
            animationLock = new FpsManager(9);
        }
        updateCollisionBox(7, 0, 18, 20);
        updateAnimationFrame();
    }

    @Override
    public void render(Graphics g) {
        if (animation == null) {
            animation = Util.getImageArray(path, 1, 1);
        }

        g.drawImage(animation[0][animationFrame % 1], x - Camera.xOffset, y, null);
//        g.fillRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height); // drawsHitBox
    }

}
