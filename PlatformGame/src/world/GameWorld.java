package world;

import actors.Actor;
import entity.Entity;
import game.Game;
import game.GameObject;
import handler.Handler;
import java.awt.Graphics;
import java.util.List;
import mapEditor.MapEditor;
import tile.Tile;
import util.Util;

public class GameWorld extends World {

    private Game game;
    private MapEditor mapEditor;

    public GameWorld(Handler handler) {
        super(handler, "resources/textures/background.png");
        tileList = Util.readWorld("resources/worlds/world1/tileFile");
        enemyList = converToEnemyFile("resources/worlds/world1/enemyFile");
        entityList = Util.readWorld("resources/worlds/world1/entityFile");
        mapEditor = new MapEditor(handler, tileList, enemyList, entityList, this);
        game = handler.getState().getGame();
    }

    @Override
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

    @Override
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
}
