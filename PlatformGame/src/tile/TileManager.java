package tile;


public class TileManager {
    
    public static final int TotalTiles = 2;
    
    public static Tile getTile(int id){
        if(id == 1){
            return new GrassTile(0, 0, "resources/textures/grass.png", 1);
        }
        if(id == 2){
            return  new StoneTile(0, 0, "resources/textures/rock.png", 2);
        }
        
        return null;
    }
    
    
    
}
