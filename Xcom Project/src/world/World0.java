package world;

import java.awt.Graphics;
import tile.RockTile;
import tile.Tile;

public class World0 extends World {
    
    public World0() {
        tileList.add(new RockTile(100, 100));
        tileList.add(new RockTile(150, 100));
        tileList.add(new RockTile(200, 200));
    }
    
    @Override
    public void update() {
        for (Tile tile : tileList) {
            tile.update();
        }
    }
    
    @Override
    public void render(Graphics g) {
        for (Tile tile : tileList) {
            tile.render(g);
        }
    }
    
}
