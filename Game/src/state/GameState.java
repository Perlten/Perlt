package state;

import entity.Enemy;
import entity.Player;
import game.Game;
import java.awt.Graphics;
import world.World;


public class GameState implements State {
    
    private World world;
    private Game game;
    private Player player;
    private Enemy enemy;
    

    public GameState(Game game) {
        this.game = game;
        world = new World("worlds/world1.txt", this);
        player = new Player("resources/textures/player.png",80, 100, 3, game, world);
        enemy = new Enemy(this, 200, 200, 1);
    }
    
    @Override
    public void update(){
        world.update();
        player.update();
        enemy.update();
        updateLevel();
    }
    @Override
    public void render(Graphics g){
        world.render(g);
        enemy.render(g);
        player.render(g);
    }

    
    public void updateLevel(){
        if(world.getStarList().isEmpty()){
            world.CreateStars(10);
            if(player.getScore() % 20 == 0){
                enemy.updateSpeed(1);
                player.updateSpeed(1);
            }
        }
    }
    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public World getWorld() {
        return world;
    }

    public Game getGame() {
        return game;
    }
    
    
    
}
