package world;

import game.GameObject;
import java.util.ArrayList;
import java.util.List;
import tile.Tile;

public abstract class World implements GameObject{

    protected List<Tile> tileList = new ArrayList<>();

    public List<Tile> getTileList() {
        return tileList;
    }
    
}
