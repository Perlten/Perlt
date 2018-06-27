package world;

import actors.Actor;
import actors.Player;
import game.GameObject;
import input.MouseInput;
import java.util.ArrayList;
import java.util.List;
import tile.Tile;

public abstract class World implements GameObject{

    protected Player player;
    
    protected List<Tile> tileList = new ArrayList<>();
    protected List<Actor> enemyList = new ArrayList<>();
    protected MouseInput mouseInput;
    
    public World(Player player, MouseInput mouseInput) {
        this.player = player;
    }
    
    public List<Tile> getTileList() {
        return tileList;
    }

    public List<Actor> getEnemyList() {
        return enemyList;
    }

    public Player getPlayer() {
        return player;
    }
}
