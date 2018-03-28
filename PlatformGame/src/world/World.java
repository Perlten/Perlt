/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import actors.Actor;
import actors.Player;
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
    protected List<Actor> enemyList;
    protected BufferedImage Background;
    
    protected Actor player;
    
    protected Handler handler;

    protected Tile currentTile;
    protected boolean editor;
    
    
    
    public World(Handler handler, String backgroundPath) {
        this.handler = handler;
        tileList = Util.readWorld("resources/worlds/world1/tileFile");
        enemyList = Util.readWorld("resources/worlds/world1/enemyFile");
        Background = Util.getImage(backgroundPath);
        currentTile = TileManager.getTile(1);
    }
    
    public abstract void update();
    public abstract void render(Graphics g);

    public List<Tile> getTileList() {
        return tileList;
    }

    public Actor getPlayer() {
        return player;
    }

    public List<Actor> getEnemyList() {
        return enemyList;
    }
}
