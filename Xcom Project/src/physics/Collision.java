package physics;

import actors.Player;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import tile.Tile;
import world.World;

public class Collision {

    private Player player;
    private World world;

    private boolean playerCollisionUp;
    private boolean playerCollisionDown;
    private boolean playerCollisionLeft;
    private boolean playerCollisionRight;

    public Collision(Player player, World world) {
        this.player = player;
        this.world = world;
    }

    public void update() {
        playerCollision();
    }

    private void playerCollision() {
        List<Tile> tileList = world.getTileList();
        Rectangle playerHB = player.getHitbox();
        playerCollisionUp = false;
        playerCollisionDown = false;
        playerCollisionLeft = false;
        playerCollisionRight = false;
        
        for (Tile tile : tileList) {
            int tileHbX = tile.getHitbox().x;
            int tileHbY = tile.getHitbox().y;
            int tileHbWidth = tile.getHitbox().width;
            int tileHbHeight = tile.getHitbox().height;

            Point tileUpperLeft = tile.getHitbox().getLocation();
            Point tileUpperRight = new Point((tileHbX + tileHbWidth), tileHbY);
            Point tileButtomLeft = new Point(tileHbX, (tileHbY + tileHbHeight));
            Point tileButtonRight = new Point(tileHbX + tileHbWidth, (tileHbY + tileHbHeight));
            //Up
            if (playerHB.contains(tileUpperLeft) || playerHB.contains(tileUpperRight)) {
                playerCollisionUp = true;
            }
            //Down
            if (playerHB.contains(tileButtomLeft) || playerHB.contains(tileButtonRight)) {
                playerCollisionDown = true;
            }
            //Left
            if (playerHB.contains(tileUpperLeft) || playerHB.contains(tileButtomLeft)) {
                playerCollisionLeft = true;
            }
            //Right
            if (playerHB.contains(tileUpperRight) || playerHB.contains(tileButtonRight)) {
                playerCollisionRight = true;
                System.out.println("ture");
            }
        }
    }

    public boolean isPlayerCollisionDown() {
        return playerCollisionDown;
    }

    public boolean isPlayerCollisionLeft() {
        return playerCollisionLeft;
    }

    public boolean isPlayerCollisionRight() {
        return playerCollisionRight;
    }

    public boolean isPlayerCollisionUp() {
        return playerCollisionUp;
    }

}
