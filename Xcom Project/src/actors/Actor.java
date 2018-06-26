package actors;

import game.GameObject;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import util.TextureUtil;

public abstract class Actor implements GameObject {

    protected int x, y;
    protected Rectangle hitbox;
    protected BufferedImage texture;
    protected String texturePath;
    protected int movementSpeed;
    
    
    public Actor(int x, int y, boolean hitbox, String texturePath, int movementSpeed) {
        this.x = x;
        this.y = y;
        this.movementSpeed = movementSpeed;
        this.texture = TextureUtil.getBufferedImage(texturePath);
        if (hitbox) {
            this.hitbox = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        }else{
            this.hitbox = new Rectangle(x, y, 0, 0);
        }
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
}
