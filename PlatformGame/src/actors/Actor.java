/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors;

import display.FpsManager;
import game.GameObject;
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
    
    protected BufferedImage[][]animation;
    protected int animationFrame = 0;
    protected int animationDirection;
    protected FpsManager animationLock;

    public Actor(int x, int y, int speed, String path, int numOfImages, int numOfDir,int frameSwitchPerSecond, World world, boolean ignoreTrans) {
        super(x, y, false, path);
        this.speed = speed;
        this.path = path;
        this.world = world;
        animation = Util.getImageArray(path, numOfImages, numOfDir);
        animationLock = new FpsManager(frameSwitchPerSecond);
        collision = new Collision(this, world, ignoreTrans);
    }

    protected void updateAnimationFrame(){
        if (animationLock.check()) {
            animationFrame++;
        }
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
}
