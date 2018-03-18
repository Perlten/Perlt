package collision;

import entity.Player;
import entity.Star;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;
import tile.Tile;
import tile.TileManager;
import world.World;

public class Collision {

    
    private Rectangle cb;
    private World world;

    public Collision(Rectangle cb, World world) {
        this.world = world;
        this.cb = cb;
    }

    public boolean checkCollisionWithTile(String direction, int speed){
        if(direction.equalsIgnoreCase("up")){
            if (!isCollidingWithSolidTile(cb.x, cb.y - speed) && !isCollidingWithSolidTile(cb.x + cb.width, cb.y - speed)) {
                return true;
            }
        }
        if(direction.equalsIgnoreCase("down")){
             if (!isCollidingWithSolidTile(cb.x, cb.y + cb.height + speed) && !isCollidingWithSolidTile(cb.x + cb.width, cb.y + cb.height + speed)) {
                return true;
            }
        }
        if(direction.equalsIgnoreCase("left")){
              if (!isCollidingWithSolidTile(cb.x - speed, cb.y) && !isCollidingWithSolidTile(cb.x - speed, cb.y + cb.height)) {
                return true;
            }
        }
        if(direction.equalsIgnoreCase("right")){
            if(!isCollidingWithSolidTile(cb.x + cb.width + speed, cb.y) && !isCollidingWithSolidTile(cb.x + cb.width + speed, cb.y + cb.height)){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean checkCollisionWithStar(String direction, int speed){
        if(direction.equalsIgnoreCase("up")){
            if (isTouchingStar(cb.x, cb.y - speed) || isTouchingStar(cb.x + cb.width, cb.y - speed)){
                 return true;
             }
        }
         if(direction.equalsIgnoreCase("down")){
             if (isTouchingStar(cb.x, cb.y + cb.height + speed) || isTouchingStar(cb.x + cb.width, cb.y + cb.height + speed)) {
                return true;
            }
        }
        if(direction.equalsIgnoreCase("left")){
              if (isTouchingStar(cb.x - speed, cb.y) || isTouchingStar(cb.x - speed, cb.y + cb.height)) {
                return true;
            }
        }
        if(direction.equalsIgnoreCase("right")){
            if(isTouchingStar(cb.x + cb.width + speed, cb.y) || isTouchingStar(cb.x + cb.width + speed, cb.y + cb.height)){
                return true;
            }
        }
        return false;
    }
    
    
    private boolean isCollidingWithSolidTile(int MoveX, int moveY) {

        int[][] tempWorld = world.getWorld();

        try {
            int goingTo = tempWorld[MoveX / 32][moveY / 32];
            Tile tile = TileManager.tileList.get(goingTo);
            if (tile.isSolid()) {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
        return false;
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
    
    public boolean collisionWithEntity(Player player, int x, int y){
        Rectangle cb = player.getCb();
            if ((x >= player.getCb().x && x <= player.getCb().x + cb.getWidth()) && (y >= player.getCb().y && player.getCb().y + cb.getHeight() >= y)) {
                return true;
            }
        return false;
    }

}