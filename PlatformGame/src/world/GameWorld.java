package world;

import actors.Actor;
import actors.Player;
import entity.Coin;
import game.Game;
import game.GameObject;
import handler.Handler;
import java.awt.Graphics;
import java.util.ArrayList;
import mapEditor.MapEditor;
import tile.Tile;
import util.Util;

public class GameWorld extends World {

    private Game game;
    private MapEditor mapEditor;

    Coin coin = new Coin(50, 50);

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
        renderTile(g);
    }

    private void renderTile(Graphics g) {
        Actor actor = handler.getActor();
        g.drawImage(Background, 0, 0, null);
        for (GameObject tile : tileList) {
            if (tile.getX() >= actor.getX() - (game.getWidth() + 32) / 2 && tile.getX() <= player.getX() + (game.getWidth() + 32) / 2) {
                tile.render(g);
            }
        }
        for (GameObject enemy : enemyList) {
            if (enemy.getX() >= actor.getX() - (game.getWidth() + 32) / 2 && actor.getX() <= player.getX() + (game.getWidth() + 32) / 2) {
                enemy.render(g);
            }
        }
        for (GameObject entity : entityList) {
            entity.render(g);
        }
        player.render(g);
        mapEditor.render(g);
    }
}
