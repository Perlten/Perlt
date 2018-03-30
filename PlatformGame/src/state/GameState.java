package state;

import display.Camera;
import game.Game;
import handler.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import world.GameWorld;
import world.World;

public class GameState implements State {
    
    private World world;
    private Handler handler;
    private Game game;
    
    private Camera cam;
    
    public GameState(Game game) {
        this.game = game;
        cam = new Camera(game);
        handler = new Handler(game.getKeyInput(), game.getMouseInput(), this);
        world = new GameWorld(handler);
        handler.setActor(world.getPlayer());
    }

    @Override
    public void update() {
        cam.focusOnActor(world.getPlayer());
        world.update();
        playerDead();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
    
    private void playerDead(){
        Rectangle col = world.getPlayer().getCollisionBox();
        if(col.y + col.height > game.getHeight() || world.getPlayer().getHealth() <= 0){
            System.exit(0);
        }
    }

    @Override
    public World getWorld() {
        return world;
    }
    
    @Override
    public Game getGame() {
        return game;
    }
}
