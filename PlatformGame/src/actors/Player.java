/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors;

import display.Camera;
import handler.Handler;
import input.KeyInput;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import world.World;

/**
 *
 * @author Perlt
 */
public class Player extends Actor {

    private KeyInput key;
    private boolean jumping;
    private double time = 0;
    

    public Player(int x, int y, int speed, Handler handler, World world) {
        super(x, y, speed, "resources/textures/player.png", handler, new Rectangle(), world, true);
        this.key = handler.getKeyInput();
    }

    @Override
    public void update() {
        updateCollisionBox(7, 11, 17, 20);
        updateCollisionWithEnemy();
        physics.update(jumping);
        jump();
        move();
    }

    @Override
    public void render(Graphics g) {
        renderPlayer(g);
        renderHealth(g);
    }

    private void move() {
            if (key.isRight()) {
                if (!collision.collisionWithSolidTile(speed, "right") || handler.getKeyInput().isEditor()) {
                    x += speed;
                }
            }
            if (key.isLeft()) {
                if (!collision.collisionWithSolidTile(speed, "left") || handler.getKeyInput().isEditor()) {
                    x -= speed;
                }
            }
    }

    private void jump() {
        double jumpSpeed = 2.0;

        if (jumping) {
            y -= jumpSpeed * time;
            time -= 0.2;
        }
        if (!physics.getGravity().isFalling() && !jumping) {
            if (key.isUp()) {
                jumping = true;
            }
        }
        if (time <= 0.0 || collision.collisionWithSolidTile((int) jumpSpeed, "up")) {
            time = 5;
            jumping = false;
        }
    }

    private void updateCollisionWithEnemy(){
        Actor enemy = collision.collisionWithActor(world.getEnemyList());
        if(enemy != null){
            health--;
            world.getEnemyList().remove(enemy);
        }
    }
    
    private void renderPlayer(Graphics g) {
            g.drawImage(texture, x - Camera.xOffset, y, null);
//            g.fillRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height); // draws collision box
    }
    
    private void renderHealth(Graphics g){
        g.drawString(String.valueOf(health), 5, 20);
    }

    public Collision getCollision() {
        return collision;
    }

    public boolean isJumping() {
        return jumping;
    }
}
