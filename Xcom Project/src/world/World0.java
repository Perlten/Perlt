package world;

import actors.Actor;
import actors.OverWorldPlayer;
import actors.Player;
import camera.Camera;
import display.Display;
import enemy.Enemy;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import npc.Npc;
import sprites.Sprite;
import state.State;
import terrain.Terrain;
import tile.Tile;

public class World0 extends OverWorld {
    
    public World0(MouseInput mouseInput, KeyInput keyInput, State state) {
        super(mouseInput, keyInput, "mainWorld", state, false);
        this.player = new OverWorldPlayer(0, 0, keyInput, this);
        this.camera = new Camera(player, Display.width, Display.height);
    }

    @Override
    public void update() {
        loadWorld();
        mapEditor.update();

        for (Terrain terrain : terrainList) {
            terrain.update();
        }
        player.update();
        camera.update();
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
        //TODO: make size dynamic
        int playerX = player.getX();
        int playerY = player.getY();

        for (Terrain terrain : terrainList) {
            if (checkRenderDistance(playerX, playerY, terrain.getX(), terrain.getY(), 192)) {
                terrain.render(g);
            }
        }

        player.render(g);

        for (Npc npc : npcList) {
            if (checkRenderDistance(playerX, playerY, npc.getX(), npc.getY(), 32)) {
                npc.render(g);
            }
        }

        for (Actor enemy : enemyList) {
            if (checkRenderDistance(playerX, playerY, enemy.getX(), enemy.getY(), 32)) {
                enemy.render(g);
            }
        }

        for (Sprite sprite : spriteList) {
            if (checkRenderDistance(playerX, playerY, sprite.getX(), sprite.getY(), 300)) {
                sprite.render(g);
            }
        }
        
        //Makes sure that chatbox is always on top
        for (Npc npc : npcList) {
            if (checkRenderDistance(playerX, playerY, npc.getX(), npc.getY(), 32)) {
                npc.renderTextBox(g);
            }
        }
        mapEditor.render(g);
    }
}
