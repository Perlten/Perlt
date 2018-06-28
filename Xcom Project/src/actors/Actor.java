package actors;

import camera.Camera;
import game.GameObject;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import physics.Collision;
import world.World;

public abstract class Actor implements GameObject, Serializable {

    protected int x, y;
    protected Rectangle hitbox;
    protected transient BufferedImage[][] texture;
    protected int movementSpeed;
    protected transient Collision collision;

    protected transient World world;

    public Actor(int x, int y, Rectangle hitbox, BufferedImage[][] texture, int movementSpeed, World world) {
        this.x = x;
        this.y = y;
        this.world = world;
        this.collision = new Collision(this, world);
        this.movementSpeed = movementSpeed;
        this.texture = texture;
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
        if (collision == null) {
            collision = new Collision(this, world);
        }
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

}
