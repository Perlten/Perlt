package actors;

import camera.Camera;
import game.GameObject;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import physics.Collision;
import util.TextureUtil;
import world.World;

public abstract class Actor implements GameObject, Serializable {

    protected int startX, startY;
    
    protected boolean moveing;
    protected transient int x, y;
    protected Rectangle hitbox;
    protected transient BufferedImage[][] texture;
    protected String texturePath = "resources/texture/player/";
    protected int numOfAnimation;
    protected int numOfFrames;
    
    protected int movementSpeed;
    protected transient Collision collision;

    protected transient World world;

    public Actor(int x, int y, Rectangle hitbox, String texturePath, int movementSpeed, World world, int numOfAnimation, int numOfFrames) {
        this.x = x;
        this.y = y;
        this.texturePath += texturePath;
        this.numOfAnimation = numOfAnimation;
        this.numOfFrames = numOfFrames;
        texture = TextureUtil.getBufferedImagePack(this.texturePath, numOfAnimation, numOfFrames);
        this.world = world;
        this.collision = new Collision(this, world);
        this.movementSpeed = movementSpeed;
        if (hitbox != null) {
            this.hitbox = new Rectangle(x, y, hitbox.width, hitbox.height);
        } else {
            this.hitbox = new Rectangle(x, y, 0, 0);
        }
    }
    

    public abstract void updateFromLoad(World world);

    protected void updateHitbox() {
        int width = hitbox.width;
        int height = hitbox.height;

        hitbox.setBounds(x, y, width, height);
    }

    protected void updateCollision() {
        collision.update();
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public Collision getCollision() {
        return collision;
    }

    public void setWorld(World world) {
        this.world = world;
        collision.setWorld(world);
    }

    @Override
    public BufferedImage getTexture() {
        return texture[0][0];
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMoveing() {
        return moveing;
    }

    public void setMoveing(boolean moveing) {
        this.moveing = moveing;
    }

    public World getWorld() {
        return world;
    }
    
}
