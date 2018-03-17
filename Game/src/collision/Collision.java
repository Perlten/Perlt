package collision;

import entity.Player;
import entity.Star;
import java.awt.image.BufferedImage;
import java.util.List;
import tile.Tile;
import tile.TileManager;
import world.World;

public class Collision {

    private Player player;
    private World world;

    public Collision(Player player, World world) {
        this.player = player;
        this.world = world;
    }

    public boolean isColliding(int MoveX, int moveY) {

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

    public boolean isTouchingStar(int x, int y) {
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
