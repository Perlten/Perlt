/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapEditor;

import display.Camera;
import handler.Handler;
import input.MouseInput;
import java.awt.Graphics;
import java.util.List;
import tile.Tile;
import tile.TileManager;
import util.Util;

/**
 *
 * @author Perlt
 */
public class MapEditor {

    private Tile currentTile;
    private boolean editor;
    private Handler handler;
    private List<Tile> tileList;

    private MouseInput mouse;

    public MapEditor(Handler handler, List<Tile> tileList) {
        this.handler = handler;
        this.tileList = tileList;
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
            if (currentTile != null) {
                currentTile.render(g);
            }
        }
    }

    private void updateEditor() {
            addTile(handler.getKeyInput().getTileId());
            removeTile();
            if (handler.getKeyInput().isSave()) {
                System.out.println("saved");
                Util.saveToFile("resources/worlds/world1/tileFile", tileList);
                handler.getKeyInput().setSaveFalse();
            }
        if (handler.getKeyInput().isDelete()) {
            tileList.clear();
        }
    }

    protected void addTile(int tileId) {
        currentTile = TileManager.getTile(handler.getKeyInput().getTileId());
        currentTile.setX(((handler.getMouseInput().getX() + Camera.xOffset) / Tile.width) * Tile.width);
        currentTile.setY((handler.getMouseInput().getY() / Tile.height) * Tile.height);

        if (handler.getMouseInput().isLeftMouseClicked()) {
            tileList.add(currentTile);
            handler.getMouseInput().setMouse1False();
            currentTile = null;
        }
    }

    protected void removeTile() {
        MouseInput mouse = handler.getMouseInput();
        if (handler.getMouseInput().isRightMouseClicked()) {
            Tile tile = FindTile(mouse.getX(), mouse.getY());
            if (tile != null) {
                tileList.remove(tile);
            }
        }
    }

    private Tile FindTile(int x, int y) {
        for (Tile tile : tileList) {
            if (tile.getCollisionBox().contains(x, y)) {
                return tile;
            }
        }
        return null;
    }
}
