/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors;

import display.Camera;
import display.FpsManager;
import handler.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import util.Util;
import world.World;

/**
 *
 * @author Perlt
 */
public class Enemy extends Actor {
    
    private boolean goingLeft = false;

    public Enemy(int x, int y, int speed, World world) {
        super(x, y, speed, "resources/textures/enemyAnimation.png", 6, 2, 9, new Rectangle(), world, false);
    }

    @Override
    public void update() {
        updateCollisionBox(7, 11, 17, 20);
        physics.update(false);
        move();
    }

    @Override
    public void render(Graphics g) {
//        if(texture == null){
//            texture = Util.getImage(path);
//        }
//        g.drawImage(texture, x - Camera.xOffset, y, null);
            g.drawImage(animation[animationDirection][animationFrame % 6], x - Camera.xOffset, y, null);
            if(animationLock.check()){
                animationFrame++;
            }
    }
    
    private void move(){
        if(collision.collisionWithSolidTile(speed, "right")){
            goingLeft = true;
        }
        if(collision.collisionWithSolidTile(speed, "left")){
            goingLeft = false;
        }
        if(goingLeft){
            x -= speed;
            animationDirection = 0;
        }else{
            x += speed;
            animationDirection = 1;
        }
    }
    
}
