package mapEditor;

import actors.Enemy;
import entity.Coin;
import entity.WinHeart;
import game.GameObject;
import handler.Handler;
import tile.GrassTile;
import tile.StoneTile;
import tile.TransparentTile;
import world.World;


public class GameObjectManager {
    
    public static final int TotalTiles = 6;
    
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
            return new Enemy(0, 0, 2, world);
        }
        if(id == 5){
            return new Coin(0, 0);
        }
        if(id == 6){
            return new WinHeart(0, 0);
        }
        return null;
    }
}
