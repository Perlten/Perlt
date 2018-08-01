package world;

import actors.BattlePlayer;
import actors.OverWorldPlayer;
import actors.Player;
import camera.Camera;
import enemy.BattleEnemy;
import enemy.Enemy;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import npc.Npc;
import sprites.Sprite;
import state.State;
import terrain.Terrain;
import tile.Tile;

public abstract class BattleWorld extends World {

    protected BattlePlayer player;

    protected List<Tile> tileList = new ArrayList<>();
    protected List<BattleEnemy> enemyList = new ArrayList<>();
    protected List<Sprite> spriteList = new ArrayList<>();
    protected List<Terrain> terrainList = new ArrayList<>();
    protected List<Npc> npcList = new ArrayList<>();

    public BattleWorld(MouseInput mouseInput, KeyInput keyInput, String worldName, State state, boolean battleWorld) {
        super(mouseInput, keyInput, worldName, state, battleWorld);
    }

    @Override
    protected void loadWorld() {
        if (!Worldloaded) {
            Worldloaded = true;
            File tileFile = new File(WorldPath + "tile");
            File enemyFile = new File(WorldPath + "enemy");
            File spriteFile = new File(WorldPath + "sprite");
            File terrainFile = new File(WorldPath + "terrain");
            File npcFile = new File(WorldPath + "npc");

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
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Tile file corrupted");
            }

            //loads enemy
            try{
                if (enemyFile.exists()) {
                    ois = new ObjectInputStream(new FileInputStream(enemyFile));
                    enemyList = (List<BattleEnemy>) ois.readObject();
                    ois.close();

                    for (BattleEnemy enemy : enemyList) {
                        enemy.updateFromLoad(this);
                    }
                } else {
                    enemyList = new ArrayList<>();
                    System.out.println("Could not find enemy file");
                }
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Enemy file corrupted");
            }
            //Load sprite
            try{ 
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
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Sprite file corrupted");
            }

            //Load terrain
            try {
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
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Terrain file corrupted");
            }

            //Load npc
            try {
                if (npcFile.exists()) {
                    ois = new ObjectInputStream(new FileInputStream(npcFile));
                    npcList = (List<Npc>) ois.readObject();
                    ois.close();

                    for (Npc npc : npcList) {
                        npc.updateFromLoad(this);
                    }
                } else {
                    npcList = new ArrayList<>();
                    System.out.println("Could not find npc file");
                }
                Camera.resetCamera();
                System.out.println("Load");
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Npc file corrupted");
            }

        }
    }

    public List<Tile> getTileList() {
        return tileList;
    }

    @Override
    public List<Enemy> getEnemyList() {
        return (List<Enemy>) (List<?>) enemyList;
    }

    @Override
    public List<Sprite> getSpriteList() {
        return spriteList;
    }

    @Override
    public List<Terrain> getTerrainList() {
        return terrainList;
    }

    @Override
    public List<Npc> getNpcList() {
        return npcList;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void addEnemy(Enemy enemy) {
        enemyList.add((BattleEnemy) enemy);
    }

    @Override
    public void addNpc(Npc npc) {
        npcList.add(npc);
    }

    @Override
    public void addSprite(Sprite sprite) {
        spriteList.add(sprite);
    }

    @Override
    public void addTerrain(Terrain terrain) {
        terrainList.add(terrain);
    }

    @Override
    public void addTile(Tile tile) {
        tileList.add(tile);
    }

    @Override
    public void removeEnemy(Enemy enemy) {
        enemyList.remove((BattleEnemy) enemy);
    }

    public List<BattleEnemy> getBattleEnemyList() {
        return enemyList;
    }

    public BattlePlayer getBattlePlayer() {
        return player;
    }

}
