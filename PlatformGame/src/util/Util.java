package util;

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

    public static void saveWorld(String path, List<Tile> tileList) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tileList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Tile> readWorld(String path) {
        try {
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fin);
            return (List<Tile>) ois.readObject();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

}
