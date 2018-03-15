package world;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tile.Tile;
import tile.TileManager;

public class World {

    private int[][] world;
    private int width, height;

    public World(String path) {
        try {
            makeWorld(path);
        } catch (IOException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update() {
        
    }

    public void render(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Tile tile = TileManager.tileList.get(world[x][y]);
                tile.render(g, x * 32, y * 32);
            }
        }
    }

    private void makeWorld(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(path));

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        String worldString = sb.toString();
        String[] temp = worldString.split("\\s+");
        
        width = Integer.parseInt(temp[0]);
        height = Integer.parseInt(temp[1]);
        

        world = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int tmp = Integer.parseInt(temp[x + y + 4]);
                world[x][y] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        World world = new World("worlds/world1.txt");
    }

}
