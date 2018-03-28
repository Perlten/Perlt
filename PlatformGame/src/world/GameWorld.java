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
        editor = handler.getKeyInput().isEditor();
        for (Tile tile : tileList) {
            tile.update();
        }
        updateEditor();
    }

    @Override
    public void render(Graphics g) {
        renderTile(g);
        if (editor) {
            if (currentTile != null) {
                currentTile.render(g);
            }
        }
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

    private void updateEditor() {
        if (editor) {
            addTile(handler.getKeyInput().getTileId());
            removeTile();
            if (handler.getKeyInput().isSave()) {
                System.out.println("saved");
                Util.saveWorld("resources/worlds/world1", tileList);
                handler.getKeyInput().setSaveFalse();
            }
        }
        if (handler.getKeyInput().isDelete()) {
            tileList.clear();
        }
    }

}
