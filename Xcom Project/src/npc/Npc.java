package npc;

import actors.Actor;
import display.FpsLock;
import java.awt.Graphics;
import java.awt.Rectangle;
import physics.Collision;
import physics.ViewLine;
import util.TextureUtil;
import world.World;

public abstract class Npc extends Actor {

    protected int direction;
    protected int frame;
    protected FpsLock animationLock = new FpsLock(5);

    public Npc(int x, int y, String texturePath, World world, int numOfAnimation, int numOfFrames) {
        super(x, y, new Rectangle(32, 32), texturePath, 1, world, numOfAnimation, numOfFrames);
        this.viewLine = new ViewLine(this, world);
    }

    @Override
    public void updateFromLoad(World world) {
        x = startX;
        y = startY;
        viewLine = new ViewLine(this, world);
        texture = TextureUtil.getBufferedImagePack(texturePath, numOfAnimation, numOfFrames);
        this.world = world;
        this.collision = new Collision(this, world);
        animationLock.reset();
        updateHitbox();
    }

    protected void animate(Graphics g) {
        if (animationLock.check() && moveing) {
            frame++;
        }
        g.drawImage(texture[direction][frame % numOfFrames], x, y, null);
    }

    @Override
    public void addGameObject(World world, int x, int y) {
        world.addNpc(this);
        this.x = x;
        this.y = y;
        startX = x;
        startY = y;
        updateHitbox();
    }
    
    public abstract void renderTextBox(Graphics g);

    public int getDirection() {
        return direction;
    }

    public abstract void playerInteract();
}
