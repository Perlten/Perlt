package edit;

import actors.Actor;
import actors.GroundEnemy;
import camera.Camera;
import java.util.ArrayList;
import java.util.List;
import sprites.BridgeSprite;
import sprites.HouseSprite;
import sprites.Sprite;
import sprites.TreeSprite;
import terrain.RockOnSand;
import terrain.Sand;
import terrain.Terrain;
import tile.PathTile;
import tile.RockTile;
import tile.Tile;
import world.World;

public class ObjectManager {

    private World world;

    private List<Tile> tileList = new ArrayList<>();
    private List<Actor> enemyList = new ArrayList<>();
    private List<Sprite> spriteList = new ArrayList<>();
    private List<Terrain> terrainList = new ArrayList<>();

    public ObjectManager(World world) {
        this.world = world;
        tileList.add(new RockTile(0, 0));

        enemyList.add(new GroundEnemy(0, 0, world));

        spriteList.add(new HouseSprite(0, 0));
        spriteList.add(new TreeSprite(0, 0));
        spriteList.add(new BridgeSprite(0, 0));

        terrainList.add(new Sand(0, 0));
        terrainList.add(new RockOnSand(0, 0));
    }

    public Tile getTile(int index, int x, int y) {
        x += Camera.xOffset;
        y += Camera.yOffset;
        switch (index) {
            case 0:
                return new RockTile(x, y);
            default:
                System.out.println("Could not find tile");
                return new RockTile(x, y);
        }
    }

    public Actor getEnemy(int index, int x, int y) {
        x += Camera.xOffset;
        y += Camera.yOffset;

        switch (index) {
            case 0:
                return new GroundEnemy(x, y, world);
            default:
                System.out.println("Could not find enemy");
                return new GroundEnemy(x, y, world);
        }
    }

    public Sprite getSprite(int index, int x, int y) {
        x += Camera.xOffset;
        y += Camera.yOffset;

        switch (index) {
            case 0:
                return new HouseSprite(x, y);
            case 1:
                return new TreeSprite(x, y);
            case 2:
                return new BridgeSprite(x, y);
            default:
                System.out.println("Could not find sprite");
                return new HouseSprite(x, y);
        }
    }

    public Terrain getTerrain(int index, int x, int y) {
        x += Camera.xOffset;
        y += Camera.yOffset;

        switch (index) {
            case 0:
                return new Sand(x, y);
            case 1:
                return new RockOnSand(x, y);
            default:
                System.out.println("Could not find terrain");
                return new Sand(x, y);
        }
    }

    public PathTile getPathTile(int index, int x, int y, int num) {
        x += Camera.xOffset;
        y += Camera.yOffset;
        return new PathTile(x, y, num);
    }

    public List<Tile> allTileList() {
        return tileList;
    }

    public List<Actor> allEnemyList() {
        return enemyList;
    }

    public List<Sprite> allSpriteList() {
        return spriteList;
    }

    public List<Terrain> allTerrainList() {
        return terrainList;
    }
}