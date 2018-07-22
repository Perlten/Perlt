package edit;

import actors.Actor;
import enemy.Enemy;
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
import npc.Npc;
import sprites.Sprite;
import terrain.Terrain;
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

    public boolean edit = false;
    
    private boolean battleWorld;

    public MapEditor(World world, KeyInput keyInput, MouseInput mouseInput, String worldPath, boolean battleWorld) {
        this.battleWorld = battleWorld;
        this.world = world;
        this.worldPath = worldPath;
        this.mgo = new ObjectManager(world, battleWorld);
        this.keyInput = keyInput;
        this.mouseInput = mouseInput;
        init();
    }

    private void init() {
        selectedObject = mgo.getTile(0, 0, 0);
        objectTypeList = Arrays.asList("tile", "enemy", "sprite", "terrain", "npc");
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
            if (keyInput.isL()) {
                deleteObject();
            }
        }
    }

    public void renderMapEditorObject(Graphics g) {
        renderHighlightedObject(g);

        for (Tile tile : world.getTileList()) {
            tile.render(g);
        }
    }

    private void updateSelectedObject() {
        if (keyInput.isI()) {
            currnetObjectType = ++currnetObjectType % objectTypeList.size();
            highlightedObject = null;
        }
        if (highlightedObject == null) {
            String type = objectTypeList.get(currnetObjectType);

            if (type.equals("tile")) {
                selectedObject = mgo.getTile(objectId, 0, 0);
            } else if (type.equals("enemy")) {
                selectedObject = mgo.getEnemy(objectId, 0, 0);
            } else if (type.equals("sprite")) {
                selectedObject = mgo.getSprite(objectId, 0, 0);
            } else if (type.equals("terrain")) {
                selectedObject = mgo.getTerrain(objectId, 0, 0);
            } else if (type.equals("npc")) {
                selectedObject = mgo.getNpc(objectId, 0, 0);
            }
        }
    }

    private void updateObjectId() {
        int temp = keyInput.lastestNumKey();
        if (temp != -1) {
            objectId = temp;
            highlightedObject = null;
        }
    }

    private void renderChoices(Graphics g) {
        int sizeX = 32;
        int sizeY = 32;
        int hint = 0;

        String type = objectTypeList.get(currnetObjectType);
        if (type.equals("tile")) {
            g.drawString("Tile", 50, 15);
            List<Tile> tileList = mgo.allTileList();
            for (int i = 0; i < tileList.size(); i++) {
                int x = i * 64 + 100;
                g.drawImage(tileList.get(i).getTexture().getScaledInstance(sizeX, sizeY, hint), x, 1, null);
                g.drawString(String.valueOf(i), x + 16, 50);
            }
        } else if (type.equals("enemy")) {
            g.drawString("Enemy", 50, 15);
            List<Enemy> enemyList = mgo.allEnemyList();
            for (int i = 0; i < enemyList.size(); i++) {
                int x = i * 64 + 100;
                g.drawImage(enemyList.get(i).getTexture().getScaledInstance(sizeX, sizeY, hint), x, 1, null);
                g.drawString(String.valueOf(i), x + 16, 50);
            }
        } else if (type.equals("sprite")) {
            g.drawString("Sprite", 50, 15);
            List<Sprite> spriteList = mgo.allSpriteList();
            for (int i = 0; i < spriteList.size(); i++) {
                int x = i * 64 + 100;
                g.drawImage(spriteList.get(i).getTexture().getScaledInstance(sizeX, sizeY, hint), x, 1, null);
                g.drawString(String.valueOf(i), x + 16, 50);
            }
        } else if (type.equals("terrain")) {
            g.drawString("Terrain", 50, 15);
            List<Terrain> terrainList = mgo.allTerrainList();
            for (int i = 0; i < terrainList.size(); i++) {
                int x = i * 64 + 100;
                g.drawImage(terrainList.get(i).getTexture().getScaledInstance(sizeX, sizeY, hint), x, 1, null);
                g.drawString(String.valueOf(i), x + 16, 50);
            }
        } else if (type.equals("npc")) {
            g.drawString("npc", 50, 15);
            List<Npc> npcList = mgo.allNpcList();
            for (int i = 0; i < npcList.size(); i++) {
                int x = i * 64 + 100;
                g.drawImage(npcList.get(i).getTexture().getScaledInstance(sizeX, sizeY, hint), x, 1, null);
                g.drawString(String.valueOf(i), x + 16, 50);
            }
        }
    }

    public void render(Graphics g) {
        if (edit) {
            renderMapEditorObject(g);
            g.translate(Camera.xOffset, Camera.yOffset);
            renderChoices(g);
            g.drawImage(selectedObject.getTexture(), getXGrid(false), getYGrid(false), null);
            g.translate(-Camera.xOffset, -Camera.yOffset);
        }
    }

    private void addObject() {
        if (highlightedObject == null) {
            selectedObject.addGameObject(world, getXGrid(true), getYGrid(true));
        } else {
            highlightedObject.addHighlightedObject(getXGrid(true), getYGrid(true));
        }
    }

    private void saveWorld() {
        try {
            File tileFile = new File(worldPath + "tile");
            File enemyFile = new File(worldPath + "enemy");
            File spriteFile = new File(worldPath + "sprite");
            File terrainFile = new File(worldPath + "terrain");
            File npcFile = new File(worldPath + "npc");

            //Save tile list
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tileFile));
            oos.writeObject(world.getTileList());
            oos.close();
            //Save enemy list.
            oos = new ObjectOutputStream(new FileOutputStream(enemyFile));
            oos.writeObject(world.getEnemyList());
            oos.close();
            //Sprite
            oos = new ObjectOutputStream(new FileOutputStream(spriteFile));
            oos.writeObject(world.getSpriteList());
            oos.close();
            //Terrain
            oos = new ObjectOutputStream(new FileOutputStream(terrainFile));
            oos.writeObject(world.getTerrainList());
            oos.close();
            //Npc
            oos = new ObjectOutputStream(new FileOutputStream(npcFile));
            oos.writeObject(world.getNpcList());
            oos.close();
            System.out.println("Saved");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private int getXGrid(boolean addOffset) {
        int x = mouseInput.getX() / gridSize * gridSize;
        if (addOffset) {
            x += Camera.xOffset;
        }
        return x;
    }

    private int getYGrid(boolean addOffset) {
        int y = mouseInput.getY() / gridSize * gridSize;
        if (addOffset) {
            y += Camera.yOffset;
        }
        return y;
    }

    private void findHighlightedObject() {
        Point mouse = mouseInput.getMousePoint();
        for (Enemy enemy : world.getEnemyList()) {
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

        for (Npc npc : world.getNpcList()) {
            if (npc.getHitbox().contains(mouse)) {
                System.out.println("Found npc");
                highlightedObject = npc;
                return;
            }
        }

        for (Sprite sprite : world.getSpriteList()) {
            if (sprite.getHitbox().contains(mouse)) {
                System.out.println("Found sprite");
                highlightedObject = sprite;
                return;
            }
        }

        for (Terrain terrain : world.getTerrainList()) {
            if (terrain.getHitbox().contains(mouse)) {
                System.out.println("Found terrain");
                highlightedObject = terrain;
                return;
            }
        }

        highlightedObject = null;
    }

    private void renderHighlightedObject(Graphics g) {
        if (highlightedObject != null) {
            highlightedObject.renderHighlight(g);
        }
    }

    private void deleteObject() {
        Point mouse = mouseInput.getMousePoint();

        Enemy enemy;
        PathTile pathTile;
        Iterator<Enemy> enemyIter = world.getEnemyList().iterator();
        //Removes enemy
        while (enemyIter.hasNext()) {
            enemy = enemyIter.next();
            if (enemy.getHitbox().contains(mouse)) {
                enemyIter.remove();
                if (highlightedObject != null && highlightedObject.equals(enemy)) {
                    highlightedObject = null;
                }
                return;
            } else {
                //removes PathTile
                Iterator<PathTile> pathIter = enemy.getPathTiles().iterator();
                while (pathIter.hasNext()) {
                    pathTile = pathIter.next();
                    if (pathTile.getHitbox().contains(mouse)) {
                        pathIter.remove();
                        enemy.removePathTile();
                        return;
                    }
                }
            }
        }
        //Removes Tile
        Tile tile;
        Iterator<Tile> tileIter = world.getTileList().iterator();
        while (tileIter.hasNext()) {
            tile = tileIter.next();
            if (tile.getHitbox().contains(mouse)) {
                tileIter.remove();
                return;
            }
        }

        //Remove npc
        Npc npc;
        Iterator<Npc> npcIter = world.getNpcList().iterator();
        while (npcIter.hasNext()) {
            npc = npcIter.next();
            if (npc.getHitbox().contains(mouse)) {
                npcIter.remove();
                return;
            }
        }

        //Remove Sprite
        Sprite sprite;
        Iterator<Sprite> spriteIter = world.getSpriteList().iterator();
        while (spriteIter.hasNext()) {
            sprite = spriteIter.next();
            if (sprite.getHitbox().contains(mouse)) {
                spriteIter.remove();
                return;
            }
        }

        //Remove terrain
        Terrain terrain;
        Iterator<Terrain> terrainIter = world.getTerrainList().iterator();
        while (terrainIter.hasNext()) {
            terrain = terrainIter.next();
            if (terrain.getHitbox().contains(mouse)) {
                terrainIter.remove();
                return;
            }
        }

    }

}
