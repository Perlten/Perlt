    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors;

import entity.Entity;
import game.GameObject;
import java.awt.Rectangle;
import tile.TransparentTile;
import world.World;

/**
 *
 * @author Perlt
 */
public class Collision {

    private Actor actor;
    private World world;
    private Rectangle actorCB;
    private boolean ignoreTrans;

    public Collision(Actor actor, World world, boolean ignoreTrans) {
        this.actor = actor;
        this.world = world;
        this.ignoreTrans = ignoreTrans;
        actorCB = actor.getCollisionBox();
    }

    public boolean collisionWithSolidTile(int speed, String direction) {
        if (direction.equalsIgnoreCase("right")) {
            for (GameObject tile : world.getTileList()) {
                if (tile.getCollisionBox().contains(actorCB.x + actorCB.width + speed, actorCB.y)
                        || tile.getCollisionBox().contains(actorCB.x + actorCB.width + speed, actorCB.y + actorCB.height)) { // right down corner
                    if (ignoreTrans && tile instanceof TransparentTile) {
                        return false;
                    }
                    if (tile.isSolid()) {
                        return true;
                    }
                }
            }
        }
        if (direction.equalsIgnoreCase("left")) {
            for (GameObject tile : world.getTileList()) {
                if (tile.getCollisionBox().contains(actorCB.x - speed, actorCB.y)
                        || tile.getCollisionBox().contains(actorCB.x - speed, actorCB.y + actorCB.height)) {
                    if (ignoreTrans && tile instanceof TransparentTile) {
                        return false;
                    }
                    if (tile.isSolid()) {
                        return true;
                    }
                }
            }
        }
        if (direction.equalsIgnoreCase("down")) {
            for (GameObject tile : world.getTileList()) {
                if (tile.getCollisionBox().contains(actorCB.x, actorCB.y + actorCB.height + speed)
                        || tile.getCollisionBox().contains(actorCB.x + actorCB.width, actorCB.y + actorCB.height + speed)) {
                    if (ignoreTrans && tile instanceof TransparentTile) {
                        return false;
                    }
                    if (tile.isSolid()) {
                        return true;
                    }
                }
            }
        }
        if (direction.equalsIgnoreCase("up")) {
            for (GameObject tile : world.getTileList()) {
                if (tile.getCollisionBox().contains(actorCB.x, actorCB.y - speed)
                        || tile.getCollisionBox().contains(actorCB.x + actorCB.width, actorCB.y - speed)) {
                    if (ignoreTrans && tile instanceof TransparentTile) {
                        return false;
                    }
                    if (tile.isSolid()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Actor collisionWithEnemy() {
        for (Actor actor : world.getEnemyList()) {
            if (checkCorners(actor)) {
                return actor;
            }
        }
        return null;
    }

    public Entity collisionWithEntity() {
        for (Entity entity : world.getEntityList()) {
            if (checkCorners(entity)) {
                return entity;
            }
        }
        return null;
    }

    private boolean checkCorners(GameObject gameObject) {
        if (gameObject.getCollisionBox().contains(actorCB.x, actorCB.y)
                || gameObject.getCollisionBox().contains(actorCB.x + actorCB.width, actorCB.y)
                || gameObject.getCollisionBox().contains(actorCB.x, actorCB.y + actorCB.height)
                || gameObject.getCollisionBox().contains(actorCB.x + actorCB.width, actorCB.y + actorCB.height) 
                || gameObject.getCollisionBox().contains(actorCB.x, actorCB.y + (actorCB.height / 2))
                || gameObject.getCollisionBox().contains(actorCB.x + actorCB.width, actorCB.y + (actorCB.height / 2))) {
            return true;
        }
        return false;
    }
}
