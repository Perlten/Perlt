package world;

import actors.Actor;
import actors.Enemy;
import actors.Player;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import sprites.Sprite;
import tile.PathTile;
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
        for (Sprite sprite : spriteList) {
            sprite.update();
        }
    }

    @Override
    public void render(Graphics g) {
        for (Actor enemy : enemyList) {
            enemy.render(g);
        }
        for (Sprite sprite : spriteList) {
            sprite.render(g);
        }
        mapEditor.render(g);
    }

}
