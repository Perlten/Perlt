package terrain;

import game.GameObject;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import util.TextureUtil;
import world.World;

public abstract class Terrain implements GameObject, Serializable {

    protected int x, y;
    protected transient BufferedImage texture;
    protected String texturePath = "resources/texture/terrain/";

    protected Rectangle hitbox;

    public Terrain(int x, int y, String texturePathEnd) {
        this.x = x;
        this.y = y;
        this.texturePath += texturePathEnd;
        this.texture = TextureUtil.getBufferedImage(texturePath);
        hitbox = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public abstract void updateFromLoad();

    @Override
    public BufferedImage getTexture() {
        return texture;
    }

    @Override
    public void addGameObject(World world, int x, int y) {
        world.addTerrain(this);
        setX(x);
        setY(y);
        updateHitbox();
    }

    protected void updateHitbox() {
        int width = hitbox.width;
        int height = hitbox.height;

        hitbox.setBounds(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

}
