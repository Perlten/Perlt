/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import display.Camera;
import handler.Handler;
import input.MouseInput;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import tile.Tile;
import tile.TileManager;
import util.Util;

/**
 *
 * @author Perlt
 */
public abstract class World {
    
    protected List<Tile> tileList;
    protected BufferedImage Background;
    protected Handler handler;

    protected Tile currentTile;
    protected boolean editor;
    
    
    
    public World(Handler handler, String backgroundPath) {
        this.handler = handler;
        tileList = Util.readWorld("resources/worlds/world1");
        Background = Util.getImage(backgroundPath);
        currentTile = TileManager.getTile(1);
    }
    
    public abstract void update();
    public abstract void render(Graphics g);
    
    protected void addTile(int tileId){
       currentTile = TileManager.getTile(tileId);
       currentTile.setX(((handler.getMouseInput().getX() + Camera.xOffset) / Tile.width) * Tile.width);
       currentTile.setY((handler.getMouseInput().getY() / Tile.height) * Tile.height);
       
       if(handler.getMouseInput().isLeftMouseClicked()){
           tileList.add(currentTile);
           handler.getMouseInput().setMouse1False();
           currentTile = null;
       }
    }
    
    protected void removeTile(){
        MouseInput mouse = handler.getMouseInput();
        if(handler.getMouseInput().isRightMouseClicked()){
            Tile tile = FindTile(mouse.getX(), mouse.getY());
            if(tile != null){
                tileList.remove(tile);
            }
        }
    }
    
    private Tile FindTile(int x, int y){
        for(Tile tile : tileList){
            if(tile.getCollisionBox().contains(x, y)){
                return tile;
            }
        }
        return null;
    }

    public List<Tile> getTileList() {
        return tileList;
    }
}
