package world;

import actors.Actor;
import game.Game;
import handler.Handler;
import java.awt.Graphics;
import tile.Tile;
import util.Util;

public class GameWorld extends World {

    private Game game;

    public GameWorld(Handler handler) {
        super(handler, "resources/textures/background.png");
        game = handler.getState().getGame();
    }

    @Override
    public void update() {
        for (Tile tile : tileList) {
            tile.update();
        }
    }

    @Override
    public void render(Graphics g) {
        renderTile(g);
    }

    private void renderTile(Graphics g) {
        Actor actor = handler.getActor();
        g.drawImage(Background, 0, 0, null);
        for (Tile tile : tileList) {
            if (tile.getX() >= actor.getX() - (game.getWidth() + 32) / 2 && tile.getX() <= actor.getX() + (game.getWidth() + 32) / 2) {
                tile.render(g);
            }
        }
    }
}
