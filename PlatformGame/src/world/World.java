/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import actors.Actor;
import actors.Enemy;
import actors.Player;
import entity.Entity;
import game.GameObject;
import handler.Handler;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import tile.Tile;
import mapEditor.GameObjectManager;
import util.EnemyWrapper;
import util.Util;

/**
 *
 * @author Perlt
 */
public abstract class World {

    protected List<Tile> tileList;
    protected List<Actor> enemyList;
    protected List<Entity> entityList;
    protected BufferedImage Background;

    protected Player player;

    protected Handler handler;

    protected GameObject currentTile;

    public World(Handler handler, String backgroundPath) {
        this.handler = handler;
        Background = Util.getImage(backgroundPath);
        player = new Player(100, 100, 3, handler, this);
        currentTile = GameObjectManager.getTile(1, handler, this);
    }

    public abstract void update();

    public abstract void render(Graphics g);

    protected List<Actor> converToEnemyFile(String path) {
        List<EnemyWrapper> list = Util.readWorld(path);
        List<Actor> enemyTempList = new ArrayList<>();
        for (EnemyWrapper x : list) {
            enemyTempList.add(new Enemy(x.getX(), x.getY(), x.getSpeed(), this));
        }
        return enemyTempList;
    }

    public List<Tile> getTileList() {
        return tileList;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Actor> getEnemyList() {
        return enemyList;
    }
}
