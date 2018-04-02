package state;

import actors.Player;
import display.Camera;
import entity.Entity;
import entity.WinHeart;
import game.Game;
import handler.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import tile.Tile;
import world.*;

public class GameState implements State {

    private World currentWorld;
    private Handler handler;
    private Game game;
    private Camera cam;

    private String stateChange;

    private int level = 1;
    private Map<String, World> worlds = new HashMap<>();

    public GameState(Game game) {
        this.game = game;
        cam = new Camera(game);
        handler = new Handler(game.getKeyInput(), game.getMouseInput(), this);
        currentWorld = new World1(handler);
        handler.setActor(currentWorld.getPlayer());
        initWorlds();
    }

    private void initWorlds() {
        worlds.put("world1", new World1(handler));
        worlds.put("world2", new World2(handler));
    }

    @Override
    public void update() {
        cam.focusOnActor(currentWorld.getPlayer());
        playerDead();
        currentWorld.update();
        checkLevel();
    }

    @Override
    public void render(Graphics g) {
        currentWorld.render(g);
    }

    private String playerDead() {
        if (!Tile.editor) {
            Rectangle col = currentWorld.getPlayer().getCollisionBox();
            if (col.y + col.height > game.getHeight() || Player.health <= 0) {
                stateChange = "endGameState";
                Player.health = 0;
            }
        }
        return null;
    }

    private void checkLevel() {
        Entity entity = currentWorld.getPlayer().getCollision().collisionWithEntity();
        if (entity instanceof WinHeart || handler.getKeyInput().isNextLevel()) {
            currentWorld.getPlayer().addToHealth(1);
            level++;
            currentWorld = worlds.get("world" + level);
            handler.getKeyInput().setNextLevelFalse();
            if(currentWorld == null){
                stateChange = "EndGameState";
            }
        }
    }

    @Override
    public String getStageChange() {
        return stateChange;
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
