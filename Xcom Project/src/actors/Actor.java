package actors;

import game.GameObject;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import physics.Collision;
import util.TextureUtil;

public abstract class Actor implements GameObject {

    protected int x, y;
    protected Rectangle hitbox;
    protected BufferedImage texture;
    protected String texturePath;
    protected int movementSpeed;
    protected Collision collision;

    public Actor(int x, int y, Rectangle hitbox, String texturePath, int movementSpeed, Collision collision) {
        this.x = x;
        this.y = y;
        this.collision = collision;
        this.movementSpeed = movementSpeed;
        this.texture = TextureUtil.getBufferedImage(texturePath);
        if (hitbox != null) {
            this.hitbox = new Rectangle(x, y, hitbox.width, hitbox.height);
        } else {
            this.hitbox = new Rectangle(x, y, 0, 0);
        }
    }

    protected void updateHitbox(){
        int width = hitbox.width;
        int height = hitbox.height;
        
        hitbox.setBounds(x, y, width, height);
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
}
