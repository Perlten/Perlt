package state;

import display.Camera;
import game.Game;
import handler.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import tile.Tile;
import world.World1;
import world.World2;
import world.World;

public class GameState implements State {

    private World currentWorld;
    private Handler handler;
    private Game game;

    private Camera cam;
    
    private Map<String, World> worlds = new HashMap<>();

    public GameState(Game game) {
        this.game = game;
        cam = new Camera(game);
        handler = new Handler(game.getKeyInput(), game.getMouseInput(), this);
        currentWorld = new World1(handler);
        handler.setActor(currentWorld.getPlayer());
        initWorlds();
    }
    
    private void initWorlds(){
        worlds.put("world1", new World1(handler));
        worlds.put("world2", new World2(handler));
    }

    @Override
    public void update() {
        cam.focusOnActor(currentWorld.getPlayer());
        currentWorld.update();
        if (!Tile.editor) {
            playerDead();
        }
        if (handler.getKeyInput().isTest()) {
            currentWorld = worlds.get("world2");
        }
    }

    @Override
    public void render(Graphics g) {
        currentWorld.render(g);
    }

    private void playerDead() {
        Rectangle col = currentWorld.getPlayer().getCollisionBox();
        if (col.y + col.height > game.getHeight() || currentWorld.getPlayer().getHealth() <= 0) {
            System.exit(0);
        }
    }

    @Override
    public World getWorld() {
        return currentWorld;
    }

    @Override
    public Game getGame() {
        return game;
    }
}
