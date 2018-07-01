package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import world.World;

public interface GameObject {
    
    public void update();
    public void render(Graphics g);
    public BufferedImage getTexture();
    public void renderHighlight(Graphics g);
    public void addGameObject(World world, int x, int y);
    public void addHighlightedObject(int x, int y);
}
