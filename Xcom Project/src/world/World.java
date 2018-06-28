package world;

import actors.Actor;
import actors.Player;
import edit.MapEditor;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tile.Tile;

public abstract class World {

    protected Player player;

    protected List<Tile> tileList = new ArrayList<>();
    protected List<Actor> enemyList = new ArrayList<>();
    protected String WorldPath = "resources/world/world";

    protected MapEditor mapEditor;

    private boolean Worldloaded;

    public World(Player player, MouseInput mouseInput, KeyInput keyInput, int worldNum) {
        WorldPath += String.valueOf(worldNum) + "/";
        this.player = player;
        this.mapEditor = new MapEditor(this, keyInput, mouseInput, WorldPath);
    }

    protected void loadWorld() {
        if (!Worldloaded) {
            Worldloaded = true;
            File tileFile = new File(WorldPath + "tile");
            File enemyFile = new File(WorldPath + "enemy");

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
                }else{
                    enemyList = new ArrayList<>();
                    System.out.println("Could not find enemy file");
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

    public Player getPlayer() {
        return player;
    }

    public abstract void update();

    public abstract void render(Graphics g);
}
