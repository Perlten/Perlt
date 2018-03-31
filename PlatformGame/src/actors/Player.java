/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors;

import display.Camera;
import entity.Entity;
import entity.WinHeart;
import handler.Handler;
import input.KeyInput;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import sun.audio.AudioPlayer;
import tile.Tile;
import util.Util;
import world.World;

public class Player extends Actor {

    private KeyInput key;
    private boolean jumping;
    private double time = 0;
    private static int health = 3;
    private static int points = 0;
    
    public Player(int x, int y, int speed, Handler handler, World world) {
        super(x, y, speed, "resources/textures/playerAnimation.png", 5, 4, 9, world, true);
        this.key = handler.getKeyInput();
    }

    @Override
    public void update() {
        updateAnimationFrame();
        updateCollisionBox(7, 0, 17, 31);
        updateCollisionWithEnemy();
        updateCollisionWithEntity();
        physics.update(jumping);
        jump();
        move();
    }

    @Override
    public void render(Graphics g) {
        renderPlayer(g);
        renderHealth(g);
        RenderPoints(g);
    }

    private void move() {
        if (key.isRight()) {
            if (!collision.collisionWithSolidTile(speed, "right") || key.isEditor()) {
                x += speed;
                animationDirection = 3;
                return;
            }
        }
        if (key.isLeft()) {
            if (!collision.collisionWithSolidTile(speed, "left") || key.isEditor()) {
                x -= speed;
                animationDirection = 1;
                return;
            }
        }
        animationDirection = 0;
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

    private void updateCollisionWithEnemy() {
        if (!Tile.editor) {
            Actor enemy = collision.collisionWithEnemy();
            if (enemy != null) {
                health--;
                world.getEnemyList().remove(enemy);
            }
        }
    }

    private void updateCollisionWithEntity() {
        Entity entity = collision.collisionWithEntity();
        if (entity != null) {
            if (!(entity instanceof WinHeart)) {
                points += entity.getPoints();
                world.getEntityList().remove(entity);
                AudioPlayer.player.start(Util.getSound("resources/sounds/coin.wav"));
            }
        }
    }

    private void renderPlayer(Graphics g) {
        g.drawImage(animation[animationDirection][animationFrame % 5], x - Camera.xOffset, y, null);
//        g.fillRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height); // draws collision box
    }

    private void renderHealth(Graphics g) {
        int imageY = 7;
        BufferedImage image = Util.getImage("resources/textures/heart.png");
        for (int i = 0; i < health; i++) {
            g.drawImage(image, i * 32, imageY, null);
        }
    }

    private void RenderPoints(Graphics g) {
        g.setColor(Color.red);
//        g.setFont(new Font("", 1, 20));
        g.drawString(String.valueOf(points), 10, 60);
    }

    public boolean isJumping() {
        return jumping;
    }

    public int getHealth() {
        return health;
    }

    public int getPoints() {
        return points;
    }
}
