package edit;

import actors.Actor;
import camera.Camera;
import game.GameObject;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;
import tile.Tile;
import world.World;

public class MapEditor {

    private World world;
    private KeyInput keyInput;
    private MouseInput mouseInput;

    private ObjectManager mgo;

    private GameObject selectedObject;
    private int objectId;

    private List<String> objectTypeList;
    private int currnetObjectType;

    public static boolean edit = false;

    public MapEditor(World world, KeyInput keyInput, MouseInput mouseInput) {
        this.world = world;
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
        System.out.println(objectId);
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
            g.drawImage(selectedObject.getTexture(), mouseInput.getMouseX(), mouseInput.getMouseY(), null);
            g.translate(-Camera.xOffset, -Camera.yOffset);
        }
    }

    private void addObject() {
        if (selectedObject instanceof Tile) {
            world.getTileList().add(mgo.getTile(objectId, mouseInput.getMouseX(), mouseInput.getMouseY()));
        } else if (selectedObject instanceof Actor) {
            world.getEnemyList().add(mgo.getEnemy(objectId, mouseInput.getMouseX(), mouseInput.getMouseY()));
        }
    }

}
