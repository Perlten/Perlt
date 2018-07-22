package world;

import actors.Actor;
import actors.OverWorldPlayer;
import actors.Player;
import camera.Camera;
import display.Display;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import npc.Npc;
import sprites.Sprite;
import state.State;
import terrain.Terrain;
import tile.Tile;

public class MenuWorld extends World {

    public MenuWorld(MouseInput mouseInput, KeyInput keyInput, State state) {
        super(mouseInput, keyInput, "menuWorld", state);
        this.player = new OverWorldPlayer(-400, -400, keyInput, this);
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
