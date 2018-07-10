package world;

import actors.Actor;
import actors.Player;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import npc.Npc;
import sprites.Sprite;
import state.State;
import terrain.Terrain;
import tile.Tile;

public class BattleWorld1 extends World {

    public BattleWorld1(MouseInput mouseInput, KeyInput keyInput, State state) {
        super(mouseInput, keyInput, "battleWorld1", state);
        this.player = new Player(-99, -99, keyInput, 3, this);
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

        for (Npc npc : npcList) {
            npc.update();
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
        for (Terrain terrain : terrainList) {
            terrain.render(g);
        }

        for (Npc npc : npcList) {
            npc.render(g);
        }

        for (Actor enemy : enemyList) {
            enemy.render(g);
        }

        for (Sprite sprite : spriteList) {
            sprite.render(g);
        }

        mapEditor.render(g);
    }

}
