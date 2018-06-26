package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

}
