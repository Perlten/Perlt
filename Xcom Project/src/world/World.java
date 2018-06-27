package world;

import actors.Actor;
import actors.Player;
import game.GameObject;
import java.util.ArrayList;
import java.util.List;
import tile.Tile;

public abstract class World implements GameObject{

    protected Player player;
    
    protected List<Tile> tileList = new ArrayList<>();
    protected List<Actor> enemyList = new ArrayList<>();

    public World(Player player) {
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
