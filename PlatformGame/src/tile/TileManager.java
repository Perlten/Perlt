package tile;


public class TileManager {
    
    public static final int TotalTiles = 3;
    
    public static Tile getTile(int id){
        if(id == 1){
            return new GrassTile(0, 0, "resources/textures/grass.png", 1);
        }
        if(id == 2){
            return  new StoneTile(0, 0, "resources/textures/rock.png", 2);
        }
        if(id == 3){
            return new TransparentTile(0, 0, "resources/textures/transparentTile.png", 3);
        }
        return null;
    }
    
    
    
}
