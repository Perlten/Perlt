package enemy;

import ai.AI;
import actors.Actor;
import display.FpsLock;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import physics.Collision;
import physics.ViewLine;
import tile.PathTile;
import util.TextureUtil;
import world.World;

public abstract class Enemy extends Actor {
    
    private static final int VIEWLINESIZE = 200;

    //Animation
    protected int direction;
    protected int frame;
    protected FpsLock animationLock = new FpsLock(5);
    protected List<PathTile> pathTiles = new ArrayList<>();
    protected AI ai;

    public Enemy(int x, int y, World world, String texturePath, int numOfAnimation, int numOfFrames, AI ai, int movementSpeed) {
        super(x, y, new Rectangle(32, 32), texturePath, movementSpeed, world, numOfAnimation, numOfFrames, VIEWLINESIZE);
        this.ai = ai;
        this.ai.setEnemy(this);
    }

    @Override
    public void updateFromLoad(World world) {
        x = startX;
        y = startY;
        texture = TextureUtil.getBufferedImagePack(texturePath, numOfAnimation, numOfFrames);
        viewLine = new ViewLine(this, world, VIEWLINESIZE);
        collision = new Collision(this, world);
        this.world = world;
        animationLock.reset();
        updateHitbox();
        moveing = false;
        
        for (PathTile pt : pathTiles) {
            pt.updateFromLoad();
        }
    }

    protected void move() {
        Point point = ai.move();
        x += point.getX();
        y += point.getY();
    }

    protected void animate(Graphics g) {
        if (animationLock.check() && moveing) {
            frame++;
        }
        g.drawImage(texture[direction][frame % numOfFrames], x, y, null);
    }
    
    protected void playerSeen() {
        if (viewLine.canSeeActor(direction, world.getPlayer())) {
            ai.playerSeen();
        }
    }

    public void removePathTile() {
        for (int i = 0; i < pathTiles.size(); i++) {
            pathTiles.get(i).setNum(i);
        }
        ai.reset();
    }

    public List<PathTile> getPathTiles() {
        return pathTiles;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    protected void renderPathTile(Graphics g) {
        for (PathTile tile : pathTiles) {
            g.drawImage(tile.getTexture(), tile.getX(), tile.getY(), null);
            g.drawString(String.valueOf(tile.getNum()), tile.getX(), tile.getY());
        }
       
    }

    protected void addPathTile(int x, int y) {
        pathTiles.add(new PathTile(x, y, pathTiles.size()));
    }

    @Override
    public void addGameObject(World world, int x, int y) {
        world.addEnemy(this);
        this.x = x;
        this.y = y;
        startX = x;
        startY = y;
        updateHitbox();
    }
}
