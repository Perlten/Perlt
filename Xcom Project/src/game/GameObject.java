package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public interface GameObject {
    
    public void update();
    public void render(Graphics g);
    public BufferedImage getTexture();
}
