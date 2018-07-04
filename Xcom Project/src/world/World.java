package world;

import actors.Actor;
import actors.Player;
import camera.Camera;
import display.Display;
import edit.MapEditor;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import sprites.Sprite;
import terrain.Terrain;
import tile.Tile;

public abstract class World {

    protected Player player;
    protected Camera camera;

    protected List<Tile> tileList = new ArrayList<>();
    protected List<Actor> enemyList = new ArrayList<>();
    protected List<Sprite> spriteList = new ArrayList<>();
    protected List<Terrain> terrainList = new ArrayList<>();

    protected String WorldPath = "resources/world/world";

    protected MapEditor mapEditor;

    private boolean Worldloaded;

    public World(MouseInput mouseInput, KeyInput keyInput, int worldNum) {
        WorldPath += String.valueOf(worldNum) + "/";
        this.mapEditor = new MapEditor(this, keyInput, mouseInput, WorldPath);
    }

    protected void loadWorld() {
        if (!Worldloaded) {
            Worldloaded = true;
            File tileFile = new File(WorldPath + "tile");
            File enemyFile = new File(WorldPath + "enemy");
            File spriteFile = new File(WorldPath + "sprite");
            File terrainFile = new File(WorldPath + "terrain");

            ObjectInputStream ois;
            try {
                //loads tile 
                if (tileFile.exists()) {
                    ois = new ObjectInputStream(new FileInputStream(tileFile));
                    tileList = (List<Tile>) ois.readObject();
                    ois.close();
                    for (Tile tile : tileList) {
                        tile.updateFromLoad();
                    }
                } else {
                    tileList = new ArrayList<>();
                    System.out.println("Could not find tile file");
                }

                //loads enemy
                if (enemyFile.exists()) {
                    ois = new ObjectInputStream(new FileInputStream(enemyFile));
                    enemyList = (List<Actor>) ois.readObject();
                    ois.close();

                    for (Actor enemy : enemyList) {
                        enemy.updateFromLoad(this);
                    }
                } else {
                    enemyList = new ArrayList<>();
                    System.out.println("Could not find enemy file");
                }

                //Load sprite
                if (spriteFile.exists()) {
                    ois = new ObjectInputStream(new FileInputStream(spriteFile));
                    spriteList = (List<Sprite>) ois.readObject();
                    ois.close();

                    for (Sprite sprite : spriteList) {
                        sprite.updateFromLoad();
                    }
                } else {
                    spriteList = new ArrayList<>();
                    System.out.println("Could not find sprite file");
                }

                //Load terrain
                if (terrainFile.exists()) {
                    ois = new ObjectInputStream(new FileInputStream(terrainFile));
                    terrainList = (List<Terrain>) ois.readObject();
                    ois.close();

                    for (Terrain terrain : terrainList) {
                        terrain.updateFromLoad();
                    }
                } else {
                    terrainList = new ArrayList<>();
                    System.out.println("Could not find terrain file");
                }

                System.out.println("Load");
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<Tile> getTileList() {
        return tileList;
    }

    public List<Actor> getEnemyList() {
        return enemyList;
    }

    public List<Sprite> getSpriteList() {
        return spriteList;
    }

    public List<Terrain> getTerrainList() {
        return terrainList;
    }

    protected boolean checkRenderDistance(int playerX, int playerY, int objectX, int ObjectY, int objectSize) {
        int width = Display.width / 2 + objectSize;
        int height = Display.height / 2 + objectSize;
        return (objectX > playerX - width && objectX < playerX + width 
                && ObjectY > playerY - height && ObjectY < playerY + height);
    }

    public Player getPlayer() {
        return player;
    }

    public abstract void update();

    public abstract void render(Graphics g);
}
