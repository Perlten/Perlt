package tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Perlt
 */
public class Tile {
    
    private BufferedImage texture;
    private boolean solid;
    private int x, y;
    private int id;

    public Tile(String path, boolean solid, int id) {
        try {
            this.texture = ImageIO.read(new File(path));
        } catch (IOException ex) {
            Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.solid = solid;
        this.id = id;
    }
    
    public void update(){
        
    }
    
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, null);
    }

    public int getId() {
        return id;
    }

    public boolean isSolid() {
        return solid;
    }
    
}
