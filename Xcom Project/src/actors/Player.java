package actors;

import display.FpsLock;
import input.KeyInput;
import java.awt.Graphics;
import java.awt.Rectangle;
import world.World;

public abstract class Player extends Actor {
    
    private static final int VIEWLINESIZE = 150;

    protected KeyInput keyInput;

    //Animation
    protected int direction;
    protected int frame;
    protected FpsLock animationLock = new FpsLock(10);
    

    public Player(int x, int y, KeyInput keyInput, World world, int movementSpeed) {
        super(x, y, new Rectangle(32, 32), "playerTexturePack.png", movementSpeed, world, 4, 7, VIEWLINESIZE);
        this.keyInput = keyInput;
    }

    protected void animate(Graphics g) {
        if (animationLock.check() && moveing) {
            frame++;
        }
        g.drawImage(texture[direction][frame % numOfFrames], x, y, null);
    }
}
