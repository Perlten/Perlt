/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapEditor;

import display.Camera;
import game.GameObject;
import handler.Handler;
import input.MouseInput;
import java.awt.Graphics;
import java.util.List;
import tile.Tile;
import util.Util;
import world.World;

/**
 *
 * @author Perlt
 */
public class MapEditor {

    private GameObject currentGameObject;
    private boolean editor;
    private Handler handler;
    private List<GameObject> gameObjectList;
    private World world;

    private MouseInput mouse;

    public MapEditor(Handler handler, List<GameObject> tileList, World world) {
        this.handler = handler;
        this.gameObjectList = tileList;
        this.world = world;
        mouse = handler.getMouseInput();
    }

    public void update() {
        editor = handler.getKeyInput().isEditor();
        if (editor) {
            updateEditor();
        }
    }

    public void render(Graphics g) {
        if (editor) {
            if (currentGameObject != null) {
                currentGameObject.render(g);
            }
        }
    }

    private void updateEditor() {
        addTile(handler.getKeyInput().getTileId());
        removeTile();
        removeLast();
        if (handler.getKeyInput().isSave()) {
            System.out.println("saved");
            Util.saveToFile("resources/worlds/world1/tileFile", gameObjectList);
            handler.getKeyInput().setSaveFalse();
        }
        if (handler.getKeyInput().isDelete()) {
            gameObjectList.clear();
        }
    }

    protected void addTile(int tileId) {
        currentGameObject = TileManager.getTile(handler.getKeyInput().getTileId(),handler, world);
        currentGameObject.setX(((handler.getMouseInput().getX() + Camera.xOffset) / Tile.width) * Tile.width);
        currentGameObject.setY((handler.getMouseInput().getY() / Tile.height) * Tile.height);

        if (handler.getMouseInput().isLeftMouseClicked()) {
            gameObjectList.add(currentGameObject);
            handler.getMouseInput().setMouse1False();
            currentGameObject = null;
        }
    }

    protected void removeTile() {
        MouseInput mouse = handler.getMouseInput();
        if (handler.getMouseInput().isRightMouseClicked()) {
            GameObject tile = FindTile(mouse.getX(), mouse.getY());
            if (tile != null) {
                gameObjectList.remove(tile);
            }
        }
    }

    public void removeLast() {
        if (handler.getKeyInput().isRemoveLast()) {
            gameObjectList.remove(gameObjectList.size() - 1);
            handler.getKeyInput().setRemoveLastFalse();
        }
    }

    private GameObject FindTile(int x, int y) {
        for (GameObject tile : gameObjectList) {
            if (tile.getCollisionBox().contains(x, y)) {
                return tile;
            }
        }
        return null;
    }
}
