package mapEditor;

import actors.Enemy;
import game.GameObject;
import handler.Handler;
import tile.GrassTile;
import tile.StoneTile;
import tile.TransparentTile;
import world.World;


public class TileManager {
    
    public static final int TotalTiles = 4;
    
    public static GameObject getTile(int id, Handler handler, World world){
        if(id == 1){
            return new GrassTile(0, 0, "resources/textures/grass.png", 1);
        }
        if(id == 2){
            return  new StoneTile(0, 0, "resources/textures/rock.png", 2);
        }
        if(id == 3){
            return new TransparentTile(0, 0, "resources/textures/transparentTile.png", 3);
        }
        if(id == 4){
            return new Enemy(0, 0, 2, handler, world);
        }
        return null;
    }
}
