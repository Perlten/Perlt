package world;

import actors.Actor;
import actors.Enemy;
import actors.Player;
import game.Game;
import handler.Handler;
import java.awt.Graphics;
import tile.Tile;
import util.Util;

public class GameWorld extends World {

    private Game game;

    public GameWorld(Handler handler) {
        super(handler, "resources/textures/background.png");
        player = new Player(100, 100, 3, handler, this);
        enemyList.add(new Enemy(150, 150, 2, handler, this));
        enemyList.add(new Enemy(100, 150, 2, handler, this));
        enemyList.add(new Enemy(200, 150, 2, handler, this));
        game = handler.getState().getGame();
    }

    @Override
    public void update() {
        Tile.editor = handler.getKeyInput().isEditor();
        for (Tile tile : tileList) {
            tile.update();
        }
        for(Actor enemy : enemyList){
            enemy.update();
        }
        player.update();
    }

    @Override
    public void render(Graphics g) {
        renderTile(g);
    }

    private void renderTile(Graphics g) {
        Actor actor = handler.getActor();
        g.drawImage(Background, 0, 0, null);
        for (Tile tile : tileList) {
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
    }
}
