package edit;

import actors.Actor;
import actors.Enemy;
import camera.Camera;
import java.util.ArrayList;
import java.util.List;
import tile.PathTile;
import tile.RockTile;
import tile.Tile;
import world.World;

public class ObjectManager {

    private World world;

    private List<Tile> tileList = new ArrayList<>();
    private List<Actor> enemyList = new ArrayList<>();

    public ObjectManager(World world) {
        this.world = world;
        tileList.add(new RockTile(0, 0));

        enemyList.add(new Enemy(0, 0, world));
    }

    public Tile getTile(int index, int x, int y) {
        x += Camera.xOffset;
        y += Camera.yOffset;
        switch (index) {
            case 0:
                return new RockTile(x, y);
            default:
                System.out.println("Could not find Tile");
                return new RockTile(x, y);
        }
    }

    public Actor getEnemy(int index, int x, int y) {
        x += Camera.xOffset;
        y += Camera.yOffset;

        switch (index) {
            case 0:
                return new Enemy(x, y, world);
            default:
                System.out.println("Could not find enemy");
                return new Enemy(x, y, world);
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

}
