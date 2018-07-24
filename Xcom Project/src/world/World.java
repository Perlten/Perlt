package world;

import enemy.Enemy;
import actors.Player;
import camera.Camera;
import display.Display;
import edit.MapEditor;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.util.List;
import npc.Npc;
import sprites.Sprite;
import state.State;
import terrain.Terrain;
import tile.Tile;
import util.TextureUtil;

public abstract class World {

    protected Camera camera;

    protected String WorldPath = "resources/world/";

    protected MapEditor mapEditor;

    protected boolean Worldloaded;
    protected KeyInput key;

    protected State state;

    public World(MouseInput mouseInput, KeyInput keyInput, String worldName, State state, boolean battleWorld) {
        WorldPath += worldName + "/";
        this.mapEditor = new MapEditor(this, keyInput, mouseInput, WorldPath, battleWorld);
        this.key = keyInput;
        this.state = state;
    }

    public void renderLoadingScrenn(Graphics g) {
        if (!Worldloaded) {
            g.drawImage(TextureUtil.getBufferedImage("resources/texture/ui/loading.png").getScaledInstance(Display.width, Display.height, 0), 0, 0, null);
        }
    }

    protected boolean checkRenderDistance(int playerX, int playerY, int objectX, int ObjectY, int objectSize) {
        int width = Display.width / 2 + objectSize;
        int height = Display.height / 2 + objectSize;
        return (objectX > playerX - width && objectX < playerX + width
                && ObjectY > playerY - height && ObjectY < playerY + height);
    }

    public KeyInput getKey() {
        return key;
    }

    public boolean isWorldloaded() {
        return Worldloaded;
    }

    public State getState() {
        return state;
    }

    public abstract void update();

    public abstract void render(Graphics g);

    protected abstract void loadWorld();

    public abstract Player getPlayer();

    public abstract List<Tile> getTileList();

    public abstract List<Enemy> getEnemyList();

    public abstract List<Sprite> getSpriteList();

    public abstract List<Terrain> getTerrainList();

    public abstract List<Npc> getNpcList();

    public abstract void addEnemy(Enemy enemy);

    public abstract void addTile(Tile tile);

    public abstract void addTerrain(Terrain terrain);

    public abstract void addNpc(Npc npc);

    public abstract void addSprite(Sprite sprite);

    public abstract void removeEnemy(Enemy enemy);
}
