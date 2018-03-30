/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapEditor;

import actors.Actor;
import actors.Enemy;
import display.Camera;
import game.GameObject;
import handler.Handler;
import input.MouseInput;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import tile.Tile;
import util.EnemyWrapper;
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
    private List<Tile> tileList;
    private List<Actor> enemyList;
    private World world;

    private MouseInput mouse;

    public MapEditor(Handler handler, List<Tile> tileList, List<Actor> enemyList, World world) {
        this.handler = handler;
        this.tileList = tileList;
        this.enemyList = enemyList;
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
            Util.saveToFile("resources/worlds/world1/tileFile", tileList);
            Util.saveToFile("resources/worlds/world1/enemyFile", createEnemySaveList());
            handler.getKeyInput().setSaveFalse();
        }
        if (handler.getKeyInput().isDelete()) {
            tileList.clear();
        }
    }

    protected void addTile(int tileId) {
        currentGameObject = GameObjectManager.getTile(handler.getKeyInput().getTileId(), handler, world);
        currentGameObject.setX(((handler.getMouseInput().getX() + Camera.xOffset) / Tile.width) * Tile.width);
        currentGameObject.setY((handler.getMouseInput().getY() / Tile.height) * Tile.height);

        if (handler.getMouseInput().isLeftMouseClicked()) {
            if (currentGameObject instanceof Tile) {
                tileList.add((Tile)currentGameObject);
                handler.getMouseInput().setMouse1False();
                currentGameObject = null;
            }
            if(currentGameObject instanceof Enemy){
                enemyList.add((Actor)currentGameObject);
                handler.getMouseInput().setMouse1False();
                currentGameObject = null;
            }
        }
    }

    protected void removeTile() {
        MouseInput mouse = handler.getMouseInput();
        if (handler.getMouseInput().isRightMouseClicked()) {
            GameObject tile = FindTile(mouse.getX(), mouse.getY());
            if (tile != null) {
                tileList.remove(tile);
            }
        }
    }

    public void removeLast() {
        if (handler.getKeyInput().isRemoveLast()) {
            tileList.remove(tileList.size() - 1);
            handler.getKeyInput().setRemoveLastFalse();
        }
    }

    private GameObject FindTile(int x, int y) {
        for (GameObject tile : tileList) {
            if (tile.getCollisionBox().contains(x, y)) {
                return tile;
            }
        }
        return null;
    }
    
    private List<EnemyWrapper> createEnemySaveList(){
        List<EnemyWrapper> list = new ArrayList<>();
        for(Actor object : enemyList){
            list.add(new EnemyWrapper(object.getX(), object.getY(), object.getSpeed(), object.getPath()));
        }
        return list;
    }
}
