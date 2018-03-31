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
import game.Game;
import game.GameObject;
import handler.Handler;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import tile.Tile;
import mapEditor.MapEditor;
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
    protected Game game;
    protected Handler handler;
    protected MapEditor mapEditor;

    public World(Handler handler, String backgroundPath) {
        this.handler = handler;
        game = handler.getState().getGame();
        Background = Util.getImage(backgroundPath);
        player = new Player(100, 150, 3, handler, this);
    }

    public void update() {
        Tile.editor = handler.getKeyInput().isEditor();
        for (GameObject tile : tileList) {
            tile.update();
        }
        for (GameObject enemy : enemyList) {
            enemy.update();
        }
        for (GameObject entity : entityList) {
            entity.update();
        }
        player.update();
        mapEditor.update();
    }

    public void render(Graphics g) {
        renderWorld(g);
}

    private void renderWorld(Graphics g) {
        g.drawImage(Background, 0, 0, null);
        for (GameObject tile : tileList) {
            if (tile.getX() >= player.getX() - (game.getWidth() + 32) / 2 && tile.getX() <= player.getX() + (game.getWidth() + 32) / 2) {
                tile.render(g);
            }
        }
        for (GameObject enemy : enemyList) {
            if (enemy.getX() >= player.getX() - (game.getWidth() + 32) / 2 && enemy.getX() <= player.getX() + (game.getWidth() + 32) / 2) {
                enemy.render(g);
            }
        }
        for (GameObject entity : entityList) {
            if (entity.getX() >= player.getX() - (game.getWidth() + 32) / 2 && entity.getX() <= player.getX() + (game.getWidth() + 32) / 2) {
                entity.render(g);
            }
        }
        player.render(g);
        mapEditor.render(g);
    }

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

    public List<Entity> getEntityList() {
        return entityList;
    }
}
