package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class TextureUtil {

    public static BufferedImage getBufferedImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException ex) {
            System.out.println("Could not find file");
            ex.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public static BufferedImage[][] getBufferedImagePack(String path, int animations, int numOfAnimations) {
            BufferedImage[][] imagePack = new BufferedImage[animations][numOfAnimations];
        try {
            BufferedImage image = ImageIO.read(new File(path));
            for (int i = 0; i < animations; i++) {
                for (int j = 0; j < numOfAnimations; j++) {
                    imagePack[i][j] = image.getSubimage(j * 32, i * 32, 32, 32);
                }
            }
        } catch (IOException ex) {
            System.out.println("Could not find file");
            ex.printStackTrace();
            System.exit(0);
        }
        return imagePack;
    }

}
