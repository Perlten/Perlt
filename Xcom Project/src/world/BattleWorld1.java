package world;

import actors.Actor;
import actors.BattlePlayer;
import actors.OverWorldPlayer;
import actors.Player;
import enemy.BattleEnemy;
import enemy.Enemy;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import npc.Npc;
import sprites.Sprite;
import state.State;
import terrain.Terrain;
import tile.Tile;

public class BattleWorld1 extends BattleWorld {

    
    public BattleWorld1(MouseInput mouseInput, KeyInput keyInput, State state) {
        super(mouseInput, keyInput, "battleWorld1", state, true);
        this.player = new BattlePlayer(20, 200, keyInput, mouseInput, this);
    }

    @Override
    public void update() {
        loadWorld();
        mapEditor.update();

        for (Terrain terrain : terrainList) {
            terrain.update();
        }
        for (Tile tile : tileList) {
            tile.update();
        }
        for (Sprite sprite : spriteList) {
            sprite.update();
        }
    }

    @Override
    public void render(Graphics g) {
        for (Terrain terrain : terrainList) {
            terrain.render(g);
        }

        player.render(g);

        for (Npc npc : npcList) {
            npc.render(g);
        }

        for (Enemy enemy : enemyList) {
            enemy.render(g);
        }

        for (Sprite sprite : spriteList) {
            sprite.render(g);
        }

        mapEditor.render(g);
    }

  


}
