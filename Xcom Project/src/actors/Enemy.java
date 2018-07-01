package actors;

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

public class Enemy extends Actor {

    //Animation
    private static final String TEXTUREPATH = "resources/texture/player/playerTexturePack.png";
    private static final int NUMOFANIMATIONS = 4;
    private static final int NUMOFFRAMES = 7;

    private int direction;
    private int frame;
    private FpsLock animationLock = new FpsLock(5);

    private transient ViewLine viewLine;

    private List<PathTile> pathTiles = new ArrayList<>();

    private AI ai;

    public Enemy(int x, int y, World world) {
        super(x, y, new Rectangle(32, 32), TextureUtil.getBufferedImagePack(TEXTUREPATH, NUMOFANIMATIONS, NUMOFFRAMES), 1, world);
        viewLine = new ViewLine(this, world);
        this.ai = new PathFollowAI(this);
    }

    @Override
    public void updateFromLoad(World world) {
        texture = TextureUtil.getBufferedImagePack(TEXTUREPATH, NUMOFANIMATIONS, NUMOFFRAMES);
        viewLine = new ViewLine(this, world);
        collision = new Collision(this, world);
        this.world = world;
        animationLock.reset();

        for (PathTile pt : pathTiles) {
            pt.updateFromLoad();
        }
    }

    @Override
    public void update() {
        updateHitbox();
        updateCollision();
        move();
        if (viewLine.canSeeActor(direction, world.getPlayer())) {
            //TODO add code
        }
    }

    private void move() {
        Point point = ai.move();
        x += point.getX();
        y += point.getY();
    }

    @Override
    public void render(Graphics g) {
        animate(g);
        g.drawPolygon(viewLine.getPolygon(direction));
    }

    private void animate(Graphics g) {
        if (animationLock.check()) {
            frame++;
        }
        g.drawImage(texture[direction][frame % NUMOFFRAMES], x, y, null);
    }
    
    public void removePathTile(){
        for(int i = 0; i < pathTiles.size(); i++){
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

    @Override
    public void renderHighlight(Graphics g) {
        for(PathTile tile : pathTiles){
            g.drawImage(tile.getTexture(), tile.getX(), tile.getY(), null);
        }
    }

    @Override
    public void addHighlightedObject(int x, int y) {
        pathTiles.add(new PathTile(x, y, pathTiles.size()));
    }
}
