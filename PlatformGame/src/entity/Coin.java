package entity;

import display.Camera;
import display.FpsManager;
import java.awt.Graphics;
import java.awt.Rectangle;
import util.Util;

public class Coin extends Entity {

    public Coin(int x, int y) {
        super(x, y, "resources/textures/coin.png", 1, new Rectangle(0, 0, 32, 32));
    }

    @Override
    public void update() {
        updateCollisionBox(7 ,0 ,18 ,20);
    }

    @Override
    public void render(Graphics g) {
        if(animation == null){
            animation = Util.getImageArray(path, 8, 1);
        }
        if(animationLock == null){
            animationLock = new FpsManager(9);
        }
        if (animationLock.check()) {
            animationFrame++;
        }
        g.drawImage(animation[0][animationFrame % 7], x - Camera.xOffset, y, null);
//        g.fillRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height); // drawsHitBox
    }
    
}
