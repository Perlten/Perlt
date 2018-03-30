package entity;

import display.FpsManager;
import game.GameObject;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity extends GameObject {
    
    protected transient BufferedImage[][] animation;
    protected int animationFrame = 0;
    protected int animationDirection;
    protected transient FpsManager animationLock;
    protected int points;
    
    public Entity(int x, int y, String path, int points, Rectangle collisionBox) {
        super(x, y, false, path);
        this.points = points;
        this.collisionBox = collisionBox;
    }

    public int getPoints() {
        return points;
    }
}
