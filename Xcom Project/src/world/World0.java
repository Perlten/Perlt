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
        super(player, mouseInput, keyInput);
        init();
    }

    private void init() {
        tileList.add(new RockTile(100, 100));
        tileList.add(new RockTile(150, 100));
        tileList.add(new RockTile(200, 200));
        enemyList.add(new Enemy(300, 300, this));
    }

    @Override
    public void update() {
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
