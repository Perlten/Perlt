package physics;

import actors.Actor;
import actors.Player;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import tile.Tile;
import world.World;

public class Collision {

    private Actor actor;
    private World world;

    private boolean playerCollisionUp;
    private boolean playerCollisionDown;
    private boolean playerCollisionLeft;
    private boolean playerCollisionRight;

    public Collision(Actor actor, World world) {
        this.actor = actor;
        this.world = world;
    }

    public void update() {
        actorCollision();
    }

    private void actorCollision() {
        List<Tile> tileList = world.getTileList();
        playerCollisionUp = false;
        playerCollisionDown = false;
        playerCollisionLeft = false;
        playerCollisionRight = false;

        int ms = actor.getMovementSpeed();

        for (Tile tile : tileList) {
            Rectangle tileHb = tile.getHitbox();

            int actorHbX = actor.getHitbox().x;
            int actorHbY = actor.getHitbox().y;
            int actorHbWidth = actor.getHitbox().width;
            int actorHbHeight = actor.getHitbox().height;
            
            Point actorUpperLeft = actor.getHitbox().getLocation();
            Point actorUpperRight = new Point((actorHbX + actorHbWidth), actorHbY);
            Point actorButtomLeft = new Point(actorHbX, (actorHbY + actorHbHeight));
            Point actorButtonRight = new Point(actorHbX + actorHbWidth, (actorHbY + actorHbHeight));

            //Up
            if (tileHb.contains(actorUpperLeft.x, actorUpperLeft.y - ms) || tileHb.contains(actorUpperRight.x, actorUpperRight.y - ms)) {
                playerCollisionUp = true;
            }
            // Down
            if (tileHb.contains(actorButtomLeft.x, actorButtomLeft.y + ms) || tileHb.contains(actorButtonRight.x, actorButtonRight.y + ms)) {
                playerCollisionDown = true;
            }
            //Left
            if (tileHb.contains(actorUpperLeft.x - ms, actorUpperLeft.y) || tileHb.contains(actorButtomLeft.x - ms, actorButtomLeft.y)) {
                playerCollisionLeft = true;
            }
            //Right
            if (tileHb.contains(actorUpperRight.x + ms, actorUpperRight.y) || tileHb.contains(actorButtonRight.x + ms, actorButtonRight.y)) {
                playerCollisionRight = true;
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
