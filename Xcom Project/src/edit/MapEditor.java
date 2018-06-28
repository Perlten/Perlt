package edit;

import actors.Actor;
import actors.Enemy;
import camera.Camera;
import game.GameObject;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tile.PathTile;
import tile.Tile;
import world.World;

public class MapEditor {

    private World world;
    private KeyInput keyInput;
    private MouseInput mouseInput;
    private String worldPath;

    private ObjectManager mgo;

    private GameObject selectedObject;
    private int objectId;

    private List<String> objectTypeList;
    private int currnetObjectType;

    private GameObject highlightedObject;

    private final int gridSize = 16;

    public static boolean edit = false;

    public MapEditor(World world, KeyInput keyInput, MouseInput mouseInput, String worldPath) {
        this.world = world;
        this.worldPath = worldPath;
        this.mgo = new ObjectManager(world);
        this.keyInput = keyInput;
        this.mouseInput = mouseInput;
        init();
    }

    private void init() {
        selectedObject = mgo.getTile(0, 0, 0);
        objectTypeList = Arrays.asList("tile", "enemy");
    }

    public void update() {
        if (keyInput.isU()) {
            edit = !edit;
        }

        if (edit) {
            updateObjectId();
            updateSelectedObject();

            if (mouseInput.isLeftMouse()) {
                addObject();
            }
            if (keyInput.isN()) {
                saveWorld();
            }
            if (mouseInput.isRightMouse()) {
                findHighlightedObject();
            }
            if(keyInput.isL()){
                deleteObject();
            }
        }
    }

    private void updateSelectedObject() {
        if (highlightedObject == null) {
            if (keyInput.isI()) {
                currnetObjectType = ++currnetObjectType % objectTypeList.size();
            }

            String type = objectTypeList.get(currnetObjectType);

            if (type.equals("tile")) {
                selectedObject = mgo.getTile(objectId, 0, 0);
            } else if (type.equals("enemy")) {
                selectedObject = mgo.getEnemy(objectId, 0, 0);
            }
        }
    }

    private void updateObjectId() {
        int temp = keyInput.lastestNumKey();
        if (temp != -1) {
            objectId = temp;
        }
    }

    private void renderChoices(Graphics g) {
        String type = objectTypeList.get(currnetObjectType);
        if (type.equals("tile")) {
            List<Tile> tileList = mgo.allTileList();
            for (int i = 0; i < tileList.size(); i++) {
                int x = i * 64 + 100;
                g.drawImage(tileList.get(i).getTexture(), x, 1, null);
                g.drawString(String.valueOf(i), x + 16, 50);
            }
        } else if (type.equals("enemy")) {
            List<Actor> enemyList = mgo.allEnemyList();
            for (int i = 0; i < enemyList.size(); i++) {
                int x = i * 64 + 100;
                g.drawImage(enemyList.get(i).getTexture(), x, 1, null);
                g.drawString(String.valueOf(i), x + 16, 50);
            }
        }
    }

    public void render(Graphics g) {
        if (edit) {
            renderHighlightedObject(g);

            g.translate(Camera.xOffset, Camera.yOffset);
            renderChoices(g);
            g.drawImage(selectedObject.getTexture(), getXGrid(), getYGrid(), null);
            g.translate(-Camera.xOffset, -Camera.yOffset);
        }
    }

    private void addObject() {
        if (highlightedObject == null) {
            if (selectedObject instanceof Tile) {
                world.getTileList().add(mgo.getTile(objectId, getXGrid(), getYGrid()));
            } else if (selectedObject instanceof Actor) {
                world.getEnemyList().add(mgo.getEnemy(objectId, getXGrid(), getYGrid()));
            }
        } else {
            if (highlightedObject instanceof Enemy) {
                Enemy enemy = (Enemy) highlightedObject;
                enemy.getPathTiles().add(mgo.getPathTile(0, getXGrid(), getYGrid(), enemy.getPathTiles().size()));
            }
        }
    }

    private void saveWorld() {
        try {
            //Save tile list
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(worldPath + "tile")));
            oos.writeObject(world.getTileList());
            oos.close();
            //Save enemy list.
            oos = new ObjectOutputStream(new FileOutputStream(new File(worldPath + "enemy")));
            oos.writeObject(world.getEnemyList());
            oos.close();
            System.out.println("Saved");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private int getXGrid() {
        return mouseInput.getX() / gridSize * gridSize;
    }

    private int getYGrid() {
        return mouseInput.getY() / gridSize * gridSize;
    }

    private void findHighlightedObject() {
        Point mouse = mouseInput.getMousePoint();
        for (Actor enemy : world.getEnemyList()) {
            if (enemy.getHitbox().contains(mouse)) {
                System.out.println("Found enemy");
                highlightedObject = enemy;
                selectedObject = new PathTile(0, 0, -1);
                return;
            }
        }

        for (Tile tile : world.getTileList()) {
            if (tile.getHitbox().contains(mouse)) {
                System.out.println("Found tile");
                highlightedObject = tile;
                return;
            }
        }
        highlightedObject = null;
    }

    private void renderHighlightedObject(Graphics g) {
        if (highlightedObject != null) {
            if (highlightedObject instanceof Enemy) {
                Enemy enemy = (Enemy) highlightedObject;
                for (PathTile tile : enemy.getPathTiles()) {
                    tile.render(g);
                }
            }
        }
    }

    private void deleteObject() {
        Point mouse = mouseInput.getMousePoint();

        Enemy enemy;
        PathTile pathTile;
        Iterator<Enemy> enemyIter = world.getEnemyList().iterator();
        while (enemyIter.hasNext()) {
            enemy = enemyIter.next();
            if (enemy.getHitbox().contains(mouse)) {
                enemyIter.remove();
            } else {
                Iterator<PathTile> pathIter = enemy.getPathTiles().iterator();
                while (pathIter.hasNext()) {
                    pathTile = pathIter.next();
                    if (pathTile.getHitbox().contains(mouse)) {
                        pathIter.remove();
                        enemy.removePathTile();
                    }
                }
            }
        }

        Tile tile;
        Iterator<Tile> tileIter = world.getTileList().iterator();
        while (tileIter.hasNext()) {
            tile = tileIter.next();
            if (tile.getHitbox().contains(mouse)) {
                tileIter.remove();
            }
        }

    }

}
