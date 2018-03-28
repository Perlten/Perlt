/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import actors.Actor;
import actors.Player;
import display.Camera;
import game.Game;
import handler.Handler;
import input.MouseInput;
import java.awt.Graphics;
import java.awt.Rectangle;
import world.GameWorld;
import world.World;

/**
 *
 * @author Perlt
 */
public class GameState implements State {
    
    private World world;
    private Actor player;
    private Handler handler;
    private Game game;
    
    private Camera cam;
    
    public GameState(Game game) {
        this.game = game;
        cam = new Camera(game);
        handler = new Handler(game.getKeyInput(), game.getMouseInput(), this);
        world = new GameWorld(handler);
        player = new Player(100, 100, 3, handler);
        handler.setActor(player);
    }

    @Override
    public void update() {
        cam.focusOnActor(player);
        world.update();
        player.update();
        playerDead();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }
    
    private void playerDead(){
        Rectangle col = player.getCollisionBox();
        if(col.y + col.height > game.getHeight()){
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
