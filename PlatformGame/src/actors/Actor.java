/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors;

import display.Camera;
import handler.Handler;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import physics.Physics;
import util.Util;
import world.World;

public abstract class Actor {

    protected int x, y;
    protected int speed;
    protected BufferedImage texture;
    protected Rectangle collisionBox;
    protected Handler handler;
    protected Collision collision;
    protected Physics physics = new Physics(this);
    protected World world;
    protected int health = 3;

    public Actor(int x, int y, int speed, String path, Handler handler, Rectangle collisionBox, World world) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.handler = handler;
        this.collisionBox = collisionBox;
        texture = Util.getImage(path);
        this.world = world;
        collision = new Collision(this, world);
    }

    public abstract void update();

    public abstract void render(Graphics g);

    protected void updateCollisionBox(int x, int y, int width, int height) {
        collisionBox.setBounds(this.x + x - Camera.xOffset, this.y + y, width, height);
    }

    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    public Collision getCollision() {
        return collision;
    }

    public int getSpeed() {
        return speed;
    }

    public void addToY(int amount) {
        y += amount;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public Handler getHandler() {
        return handler;
    }

    public int getHealth() {
        return health;
    }
    
}
