package actors;

import game.GameObject;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import physics.Collision;
import util.TextureUtil;
import world.World;

public abstract class Actor implements GameObject {

    protected int x, y;
    protected Rectangle hitbox;
    protected BufferedImage[][] texture;
    protected int movementSpeed;
    protected Collision collision;
    
    protected World world;

    public Actor(int x, int y, Rectangle hitbox, BufferedImage[][] texture, int movementSpeed, World world) {
        this.x = x;
        this.y = y;
        this.collision = new Collision(this, world);
        this.movementSpeed = movementSpeed;
        this.texture = texture;
        if (hitbox != null) {
            this.hitbox = new Rectangle(x, y, hitbox.width, hitbox.height);
        } else {
            this.hitbox = new Rectangle(x, y, 0, 0);
        }
    }

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
    
}
