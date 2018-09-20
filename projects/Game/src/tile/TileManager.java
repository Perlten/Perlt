package tile;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Perlt
 */
public class TileManager {
    
    
    public static List<Tile> tileList; 

    public static void init(){
        tileList = new ArrayList<>();
        tileList.add(new Tile("resources/textures/grass.png", false, 0));
        tileList.add(new Tile("resources/textures/rock.png", true, 1));
    }
    
}
