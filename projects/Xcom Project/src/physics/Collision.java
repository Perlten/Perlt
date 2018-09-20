package physics;

import actors.Actor;
import actors.Player;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import tile.Tile;
import world.World;

public class Collision {

    private Actor actor;
    private World world;

    private boolean actorCollisionUp;
    private boolean actorCollisionDown;
    private boolean actorCollisionLeft;
    private boolean actorCollisionRight;

    public Collision(Actor actor, World world) {
        this.actor = actor;
        this.world = world;
    }

    public void update() {
        actorCollision();
    }

    private void actorCollision() {
        List<Tile> tileList = world.getTileList();
        actorCollisionUp = false;
        actorCollisionDown = false;
        actorCollisionLeft = false;
        actorCollisionRight = false;

        int ms = actor.getMovementSpeed();

        for (Tile tile : tileList) {
            Rectangle tileHb = tile.getHitbox();

            List<Point> pointList = getPoints();
            
            Point actorUpperLeft = pointList.get(0);
            Point actorUpperRight = pointList.get(1);
            Point actorButtomLeft = pointList.get(2);
            Point actorButtonRight = pointList.get(3);

            //Up
            if (tileHb.contains(actorUpperLeft.x, actorUpperLeft.y - ms) || tileHb.contains(actorUpperRight.x, actorUpperRight.y - ms)) {
                actorCollisionUp = true;
            }
            // Down
            if (tileHb.contains(actorButtomLeft.x, actorButtomLeft.y + ms) || tileHb.contains(actorButtonRight.x, actorButtonRight.y + ms)) {
                actorCollisionDown = true;
            }
            //Left
            if (tileHb.contains(actorUpperLeft.x - ms, actorUpperLeft.y) || tileHb.contains(actorButtomLeft.x - ms, actorButtomLeft.y)) {
                actorCollisionLeft = true;
            }
            //Right
            if (tileHb.contains(actorUpperRight.x + ms, actorUpperRight.y) || tileHb.contains(actorButtonRight.x + ms, actorButtonRight.y)) {
                actorCollisionRight = true;
            }
        }
    }

    public List<Point> getPoints() {
        List<Point> list = new ArrayList<>();

        int actorHbX = actor.getHitbox().x;
        int actorHbY = actor.getHitbox().y;
        int actorHbWidth = actor.getHitbox().width;
        int actorHbHeight = actor.getHitbox().height;

        list.add(actor.getHitbox().getLocation());
        list.add(new Point((actorHbX + actorHbWidth), actorHbY));
        list.add(new Point(actorHbX, (actorHbY + actorHbHeight)));
        list.add(new Point(actorHbX + actorHbWidth, (actorHbY + actorHbHeight)));
        return list;
    }

    public boolean isActorCollisionDown() {
        return actorCollisionDown;
    }

    public boolean isActorCollisionLeft() {
        return actorCollisionLeft;
    }

    public boolean isActorCollisionRight() {
        return actorCollisionRight;
    }

    public boolean isActorCollisionUp() {
        return actorCollisionUp;
    }

    public void setWorld(World world) {
        this.world = world;
    }

}
