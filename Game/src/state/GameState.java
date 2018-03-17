package state;

import entity.Player;
import game.Game;
import java.awt.Graphics;
import world.World;

/**
 *
 * @author Perlt
 */
public class GameState {
    
    private World world;
    private Game game;
    private Player player;

    public GameState(Game game) {
        this.game = game;
        world = new World("worlds/world1.txt");
        this.player = new Player("resources/textures/ezio.png",100, 100, game, world);
    }
    
    
    public void update(){
        world.update();
        player.update();
    }
    
    public void render(Graphics g){
        world.render(g);
        player.render(g);
    }

    public Player getPlayer() {
        return player;
    }
}
