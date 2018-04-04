/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors;

import display.Camera;
import java.awt.Graphics;
import java.util.Random;
import world.World;

/**
 *
 * @author Perlt
 */
public class Enemy extends Actor {

    private boolean goingLeft;

    public Enemy(int x, int y, int speed, World world) {
        super(x, y, speed, "resources/textures/enemyAnimation.png", 6, 2, 9, world, false);
//        goingLeft = new Random().nextBoolean(); //Uncomment if the enemy should start in a random direction
    }

    @Override
    public void update() {
        updateAnimationFrame();
        updateCollisionBox(6, 4, 23, 24);
        physics.update(false);
        move();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animation[animationDirection][animationFrame % 6], x - Camera.xOffset, y, null);
//             g.fillRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height); // draws collision box
    }

    private void move() {
        if (collision.collisionWithSolidTile(speed, "right")) {
            goingLeft = true;
        }
        if (collision.collisionWithSolidTile(speed, "left")) {
            goingLeft = false;
        }
        if (goingLeft) {
            x -= speed;
            animationDirection = 0;
        } else {
            x += speed;
            animationDirection = 1;
        }
    }

}
