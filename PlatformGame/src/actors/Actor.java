/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors;

import display.Camera;
import display.FpsManager;
import game.GameObject;
import handler.Handler;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import physics.Physics;
import util.Util;
import world.World;

public abstract class Actor extends GameObject {

    protected int speed;
    protected Collision collision;
    protected Physics physics = new Physics(this);
    protected World world;
    protected int health = 3;
    
    protected BufferedImage[][]animation;
    protected int animationFrame = 0;
    protected int animationDirection = 0;
    protected FpsManager animationLock;

    public Actor(int x, int y, int speed, String path, int numOfImages, int numOfDir,int animationframes, Rectangle collisionBox, World world, boolean ignoreTrans) {
        super(x, y, false, path);
        this.speed = speed;
        this.collisionBox = collisionBox;
        this.path = path;
        animation = Util.getImageArray(path, numOfImages, numOfDir);
        animationLock = new FpsManager(animationframes);
        this.world = world;
        collision = new Collision(this, world, ignoreTrans);
    }

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

    public BufferedImage[][] getTexture() {
        return animation;
    }

    public int getHealth() {
        return health;
    }
    
}
