package world;

import actors.Actor;
import actors.Enemy;
import actors.Player;
import game.Game;
import game.GameObject;
import handler.Handler;
import java.awt.Graphics;
import mapEditor.MapEditor;
import tile.Tile;
import util.Util;

public class GameWorld extends World {

    private Game game;
    private MapEditor mapEditor;
    
    public GameWorld(Handler handler) {
        super(handler, "resources/textures/background.png");
        player = new Player(100, 100, 3, handler, this);
        mapEditor = new MapEditor(handler, tileList, this);
        game = handler.getState().getGame();
    }

    @Override
    public void update() {
        Tile.editor = handler.getKeyInput().isEditor();
        for (GameObject tile : tileList) {
            tile.update();
        }
        for(Actor enemy : enemyList){
            enemy.update();
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
        for (Actor enemy : enemyList) {
            if (enemy.getX() >= actor.getX() - (game.getWidth() + 32) / 2 && actor.getX() <= player.getX() + (game.getWidth() + 32) / 2) {
                enemy.render(g);
            }
        }
        player.render(g);
        mapEditor.render(g);
    }
}
