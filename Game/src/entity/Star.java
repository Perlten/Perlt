package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Perlt
 */
public class Star {
    
    private BufferedImage texture;
    private int x, y;

    public Star(int x, int y) {
        try {
            texture = ImageIO.read(new File("resources/textures/star.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.x = x;
        this.y = y;
    }
    
    public void update(){
    }
    
    public void render(Graphics g){
        g.drawImage(texture, x, y, null);
    }

    public BufferedImage getTexture() {
        return texture;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
