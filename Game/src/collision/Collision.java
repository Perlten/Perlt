package collision;

import entity.Player;
import entity.Star;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import tile.Tile;
import tile.TileManager;
import world.World;

public class Collision {

    private Player player;
    private Rectangle cb;
    private World world;

    public Collision(Player player, World world) {
        this.player = player;
        this.world = world;
        this.cb = player.getCb();
    }

    public boolean checkCollisionWithTile(String direction){
        if(direction.equalsIgnoreCase("up")){
            if (isColliding(cb.x, cb.y - 3) && isColliding(cb.x + cb.width, cb.y - 3)) {
                return true;
            }
        }
        if(direction.equalsIgnoreCase("down")){
             if (isColliding(cb.x, cb.y + cb.height + 3) && isColliding(cb.x + cb.width, cb.y + cb.height + 3)) {
                return true;
            }
        }
        if(direction.equalsIgnoreCase("left")){
              if (isColliding(cb.x - 3, cb.y) && isColliding(cb.x - 3, cb.y + cb.height)) {
                return true;
            }
        }
        if(direction.equalsIgnoreCase("right")){
            if(isColliding(cb.x + cb.width + 3, cb.y) && isColliding(cb.x + cb.width + 3, cb.y + cb.height)){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean checkCollisionWithStar(String direction){
        if(direction.equalsIgnoreCase("up")){
            if (isTouchingStar(cb.x, cb.y - 3) || isTouchingStar(cb.x + cb.width, cb.y - 3)){
                 return true;
             }
        }
         if(direction.equalsIgnoreCase("down")){
             if (isTouchingStar(cb.x, cb.y + cb.height + 3) || isTouchingStar(cb.x + cb.width, cb.y + cb.height + 3)) {
                return true;
            }
        }
        if(direction.equalsIgnoreCase("left")){
              if (isTouchingStar(cb.x - 3, cb.y) || isTouchingStar(cb.x - 3, cb.y + cb.height)) {
                return true;
            }
        }
        if(direction.equalsIgnoreCase("right")){
            if(isTouchingStar(cb.x + cb.width + 3, cb.y) || isTouchingStar(cb.x + cb.width + 3, cb.y + cb.height)){
                return true;
            }
        }
        return false;
    }
    
    
    private boolean isColliding(int MoveX, int moveY) {

        int[][] tempWorld = world.getWorld();

        try {
            int goingTo = tempWorld[MoveX / 32][moveY / 32];
            Tile tile = TileManager.tileList.get(goingTo);
            if (tile.isSolid()) {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    private boolean isTouchingStar(int x, int y) {
        List<Star> starList = world.getStarList();
        for (Star star : starList) {
            BufferedImage texture = star.getTexture();
            if ((x >= star.getX() && x <= star.getX() + texture.getWidth()) && (y >= star.getY() && star.getY() + texture.getHeight() >= y)) {
                starList.remove(star);
                return true;
            }
        }
        return false;
    }

}
