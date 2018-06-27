package actors;

import display.FpsLock;
import java.awt.Graphics;
import java.awt.Rectangle;
import physics.ViewLine;
import util.TextureUtil;
import world.World;

public class Enemy extends Actor {
    //Animation
    private static final String TEXTUREPATH = "resources/texture/player/playerTexturePack.png";
    private static final int NUMOFANIMATIONS = 4;
    private static final int NUMOFFRAMES = 7;
    private int direction;
    private int frame;
    private FpsLock animationLock = new FpsLock(5);
    
    private ViewLine viewLine;
    

    public Enemy(int x, int y, int movementSpeed, World world) {
        super(x, y, new Rectangle(32, 32), TextureUtil.getBufferedImagePack(TEXTUREPATH, NUMOFANIMATIONS, NUMOFFRAMES), movementSpeed, world);
        viewLine = new ViewLine(this, world);
    }

    @Override
    public void update() {
        if(viewLine.canSeeActor(direction, world.getPlayer())){
            System.out.println("SEEEE!!!");
        }
    }

    @Override
    public void render(Graphics g) {
        animate(g);
        g.drawPolygon(viewLine.getPolygon(direction));
    }

    private void animate(Graphics g) {
        if (animationLock.check()) {
            frame = ++frame % NUMOFFRAMES;
        }
        g.drawImage(texture[direction][frame], x, y, null);
    }

}
