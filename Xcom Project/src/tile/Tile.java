package tile;

import game.GameObject;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import util.TextureUtil;
import world.World;

public abstract class Tile implements GameObject, Serializable {

    protected int x, y;
    protected String texturePath = "resources/texture/tile/";
    protected transient BufferedImage texture;
    protected Rectangle hitbox;

    protected boolean loaded;
    
    public Tile(int x, int y, Rectangle hitbox, String texturePathEnd) {
        this.x = x;
        this.y = y;
        this.texturePath += texturePathEnd;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    
    @Override
    public void addGameObject(World world, int x, int y) {
        world.getTileList().add(this);
        setX(x);
        setY(y);
    }
    
    

}
