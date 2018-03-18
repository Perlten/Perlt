package state;

import entity.Enemy;
import entity.Player;
import game.Game;
import java.awt.Graphics;
import world.World;


public class GameState {
    
    private World world;
    private Game game;
    private Player player;
    private Enemy enemy;
    

    public GameState(Game game) {
        this.game = game;
        world = new World("worlds/world1.txt", this);
        player = new Player("resources/textures/player.png",80, 100, game, world);
        enemy = new Enemy(player, world, 200, 200, 1);
    }
    
    public void update(){
        world.update();
        player.update();
        enemy.update();
        updateLevel();
    }
    
    public void render(Graphics g){
        world.render(g);
        enemy.render(g);
        player.render(g);
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }
    
    public void updateLevel(){
        if(world.getStarList().size() == 0){
            world.CreateStars(10);
            enemy.updateSpeed(1);
        }
    }
    
}
