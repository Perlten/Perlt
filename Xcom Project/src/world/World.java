package world;

import actors.Actor;
import actors.Player;
import edit.MapEditor;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import tile.Tile;

public abstract class World{

    protected Player player;
    
    protected List<Tile> tileList = new ArrayList<>();
    protected List<Actor> enemyList = new ArrayList<>();
    
    protected MapEditor mapEditor;
    
    public World(Player player, MouseInput mouseInput, KeyInput keyInput) {
        this.player = player;
        this.mapEditor = new MapEditor(this, keyInput, mouseInput);
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
    
    public abstract void update();
    public abstract void render(Graphics g);
}
