/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapEditor;

import actors.*;
import display.Camera;
import entity.Entity;
import game.GameObject;
import handler.Handler;
import input.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import tile.Tile;
import util.*;
import world.World;

public class MapEditor {

    private GameObject currentGameObject;
    private boolean editor;
    private List<Tile> tileList;
    private List<Actor> enemyList;
    private List<Entity> entityList;
    private List<GameObject> tempList = new ArrayList<>();
    private Handler handler;
    private World world;

    private MouseInput mouse;
    private KeyInput key;
    private String path;
    
    public MapEditor(Handler handler, List<Tile> tileList, List<Actor> enemyList,List<Entity> entityList, World world, String path) {
        this.tileList = tileList;
        this.enemyList = enemyList;
        this.entityList = entityList;
        this.world = world;
        this.handler = handler;
        this.path = path;
        mouse = handler.getMouseInput();
        key = handler.getKeyInput();
    }

    public void update() {
        editor = key.isEditor();
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
        addTile(key.getTileId());
        removeGameObject();
        removeLast();
        if (key.isSave()) {
            System.out.println("saved");
            Util.saveToFile(path + "/tileFile", tileList);
            Util.saveToFile(path + "/enemyFile", createEnemySaveList());
            Util.saveToFile(path + "/entityFile", entityList);
            key.setSaveFalse();
        }
        if (key.isDelete()) {
            tileList.clear();
            tempList.clear();
            enemyList.clear();
            entityList.clear();
        }
    }

    protected void addTile(int tileId) {
        currentGameObject = GameObjectManager.getTile(tileId, handler, world);
        currentGameObject.setX(((mouse.getX() + Camera.xOffset) / Tile.width) * Tile.width);
        currentGameObject.setY((mouse.getY() / Tile.height) * Tile.height);

        if (mouse.isLeftMouseClicked()) {
            mouse.setMouse1False();
            tempList.add(currentGameObject);
            if (currentGameObject instanceof Tile) {
                tileList.add((Tile) currentGameObject);
                currentGameObject = null;
            }
            if (currentGameObject instanceof Enemy) {
                enemyList.add((Actor) currentGameObject);
                currentGameObject = null;
            }
            if(currentGameObject instanceof Entity){
                entityList.add((Entity) currentGameObject);
                currentGameObject = null;
            }
        }
    }

    protected void removeGameObject() {
        if (mouse.isRightMouseClicked()) {
            Tile tile = findTile(mouse.getX(), mouse.getY());
            if (tile != null) {
                tileList.remove(tile);
                return;
            }
            Actor actor = findActor(mouse.getX(), mouse.getY());
            if (actor != null) {
                enemyList.remove(actor);
                return;
            }
            Entity entity = findEntity(mouse.getX(), mouse.getY());
            if(entity != null){
                entityList.remove(entity);
                return;
            }
        }
    }

    public void removeLast() {
        if (key.isRemoveLast()) {
            key.setRemoveLastFalse();
            if (tempList.size() < 1) {
                return;
            }
            GameObject gameObject = tempList.get(tempList.size() - 1);
            for (Actor actor : enemyList) {
                if (actor.equals(gameObject)) {
                    enemyList.remove(actor);
                    tempList.remove(tempList.size() - 1);
                    return;
                }
            }
            for (Tile tile : tileList) {
                if (tile.equals(gameObject)) {
                    tileList.remove(tile);
                    tempList.remove(tempList.size() - 1);
                    return;
                }
            }
        }
    }

    private Tile findTile(int x, int y) {
        for (Tile tile : tileList) {
            if (tile.getCollisionBox().contains(x, y)) {
                return tile;
            }
        }
        return null;
    }

    private Actor findActor(int x, int y) {
        for (Actor actor : enemyList) {
            if (actor.getCollisionBox().contains(x, y)) {
                return actor;
            }
        }
        return null;
    }
    
    private Entity findEntity(int x, int y){
        for(Entity entity : entityList){
            if(entity.getCollisionBox().contains(x, y)){
                return entity;
            }
        }
        return null;
    }
    
    private List<EnemyWrapper> createEnemySaveList() {
        List<EnemyWrapper> list = new ArrayList<>();
        for (Actor object : enemyList) {
            list.add(new EnemyWrapper(object.getX(), object.getY(), object.getSpeed(), object.getPath()));
        }
        return list;
    }
}
