package collision;

import entity.Player;
import tile.Tile;
import tile.TileManager;
import world.World;

/**
 *
 * @author Perlt
 */
public class Collision {
    
    private Player player;
    private World world;

    public Collision(Player player, World world) {
        this.player = player;
        this.world = world;
    }
    
    public boolean isColliding(int MoveX, int moveY){
        
        int[][] tempWorld = world.getWorld();
        
        int goingTo = tempWorld[MoveX / 32][moveY / 32];
        Tile tile = TileManager.tileList.get(goingTo);
        
        if(tile.isSolid()){
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(100 / 32);
    }
    
}
