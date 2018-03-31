package state;

import display.Camera;
import game.Game;
import handler.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import world.GameWorld;
import world.TestWorld;
import world.World;

public class GameState implements State {
    
    private World currentWorld;
    private Handler handler;
    private Game game;
    
    private Camera cam;
    
    public GameState(Game game) {
        this.game = game;
        cam = new Camera(game);
        handler = new Handler(game.getKeyInput(), game.getMouseInput(), this);
        currentWorld = new GameWorld(handler);
        handler.setActor(currentWorld.getPlayer());
    }

    @Override
    public void update() {
        cam.focusOnActor(currentWorld.getPlayer());
        currentWorld.update();
        playerDead();
        if(handler.getKeyInput().isTest()){
            currentWorld = new TestWorld(handler);
        }
    }

    @Override
    public void render(Graphics g) {
        currentWorld.render(g);
    }
    
    private void playerDead(){
        Rectangle col = currentWorld.getPlayer().getCollisionBox();
        if(col.y + col.height > game.getHeight() || currentWorld.getPlayer().getHealth() <= 0){
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
