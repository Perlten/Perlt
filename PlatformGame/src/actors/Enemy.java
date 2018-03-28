/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors;

import display.Camera;
import handler.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import util.Util;

/**
 *
 * @author Perlt
 */
public class Enemy extends Actor {
    
    private boolean goingLeft = false;

    public Enemy(int x, int y, int speed, Handler handler) {
        super(x, y, speed, "resources/textures/enemy.png", handler, new Rectangle());
    }

    @Override
    public void update() {
        updateCollisionBox(7, 11, 17, 20);
        physics.update(false);
        move();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture, x - Camera.xOffset, y, null);
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
        }else{
            x += speed;
        }
    }
    
}
