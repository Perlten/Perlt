package world;

import actors.Actor;
import actors.Enemy;
import actors.Player;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import tile.RockTile;
import tile.Tile;

public class World0 extends World {

    public World0(Player player, MouseInput mouseInput, KeyInput keyInput) {
        super(player, mouseInput, keyInput, 0);
        init();
    }

    private void init() {
        
    }

    @Override
    public void update() {
        loadWorld();
        mapEditor.update();
        for (Tile tile : tileList) {
            tile.update();
        }
        for (Actor enemy : enemyList) {
            enemy.update();
        }
    }

    @Override
    public void render(Graphics g) {
        mapEditor.render(g);
        for (Tile tile : tileList) {
            tile.render(g);
        }
        for (Actor enemy : enemyList) {
            enemy.render(g);
        }
    }

}
