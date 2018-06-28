package tile;

import game.GameObject;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import util.TextureUtil;

public abstract class Tile implements GameObject, Serializable {

    protected int x, y;
    protected String texturePath;
    protected transient BufferedImage texture;
    protected Rectangle hitbox;

    protected boolean loaded;
    
    public Tile(int x, int y, Rectangle hitbox, String texturePath) {
        this.x = x;
        this.y = y;
        this.texturePath = texturePath;
        this.texture = TextureUtil.getBufferedImage(texturePath);
        if (hitbox != null) {
            this.hitbox = new Rectangle(x, y, hitbox.width, hitbox.height);
        } else {
            this.hitbox = new Rectangle(x, y, 0, 0);
        }
    }

    public abstract void updateFromLoad();
    
    protected void updateHitbox() {
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

    @Override
    public BufferedImage getTexture() {
        return texture;
    }

}
