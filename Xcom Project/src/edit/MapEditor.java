package edit;

import actors.Actor;
import camera.Camera;
import game.GameObject;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            if (keyInput.isI()) {
                currnetObjectType = ++currnetObjectType % objectTypeList.size();
            }
            if (mouseInput.isLeftMouse()) {
                addObject();
            }
        }
    }

    private void updateSelectedObject() {
        String type = objectTypeList.get(currnetObjectType);
        if (type.equals("tile")) {
            selectedObject = mgo.getTile(objectId, 0, 0);
        } else if (type.equals("enemy")) {
            selectedObject = mgo.getEnemy(objectId, 0, 0);
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
            selectedObject = mgo.getTile(objectId, 0, 0);
        } else if (type.equals("enemy")) {
            List<Actor> enemyList = mgo.allEnemyList();
            for (int i = 0; i < enemyList.size(); i++) {
                int x = i * 64 + 100;
                g.drawImage(enemyList.get(i).getTexture(), x, 1, null);
                g.drawString(String.valueOf(i), x + 16, 50);
            }
            selectedObject = mgo.getEnemy(objectId, 0, 0);
        }
    }

    public void render(Graphics g) {
        if (edit) {
            g.translate(Camera.xOffset, Camera.yOffset);
            renderChoices(g);
            g.drawImage(selectedObject.getTexture(), (mouseInput.getX() / gridSize) * gridSize, (mouseInput.getY() / gridSize) * gridSize, null);
            g.translate(-Camera.xOffset, -Camera.yOffset);
        }
    }

    private void addObject() {
        if (selectedObject instanceof Tile) {
            world.getTileList().add(mgo.getTile(objectId, (mouseInput.getX() / gridSize) * gridSize, (mouseInput.getY() / gridSize) * gridSize));
        } else if (selectedObject instanceof Actor) {
            world.getEnemyList().add(mgo.getEnemy(objectId, (mouseInput.getX() / gridSize) * gridSize, (mouseInput.getY() / gridSize) * gridSize));
        }
        saveWorld();
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

}
