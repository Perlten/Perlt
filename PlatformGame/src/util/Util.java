package util;

import game.GameObject;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import tile.Tile;


public class Util {

    public static BufferedImage getImage(String path) {
        BufferedImage texture = null;
        try {
            texture = ImageIO.read(new File(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (texture == null) {
            throw new RuntimeException("Texture cannot be null");
        }
        return texture;
    }

    public static BufferedImage[][] getImageArray(String path, int numOfImages, int numOfDirections){
        BufferedImage[][] arr = new BufferedImage[numOfDirections][numOfImages];
        BufferedImage image = getImage(path);
        int xSize = image.getWidth() / numOfImages;
        int ySize = image.getHeight() / numOfDirections;
        
        for(int y = 0; y < numOfDirections; y++){
            for(int x = 0; x < numOfImages; x++){
                arr[y][x] = image.getSubimage(x * xSize, y * ySize, Tile.width, Tile.height);
            }
        }
        
        return arr;
    }
    
    public static void saveToFile(String path, List list) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List readWorld(String path) {
        try {
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fin);
            return (List) ois.readObject();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

}
