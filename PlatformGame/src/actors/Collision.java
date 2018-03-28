/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors;

import java.awt.Rectangle;
import tile.Tile;
import world.World;

/**
 *
 * @author Perlt
 */
public class Collision {

    private Actor actor;
    private World world;
    private Rectangle actorCB;

    public Collision(Actor actor, World world) {
        this.actor = actor;
        this.world = world;
        actorCB = actor.getCollisionBox();
    }

    public boolean collisionWithSolidTile(int speed, String direction) {
        if (direction.equalsIgnoreCase("right")) {
            for (Tile tile : world.getTileList()) {
                if (tile.getCollisionBox().contains(actorCB.x + actorCB.width + speed, actorCB.y)
                        || tile.getCollisionBox().contains(actorCB.x + actorCB.width + speed, actorCB.y + actorCB.width)) { // right down corner
                    if (tile.isSolid()) {
                        return true;
                    }
                }
            }
        }
        if (direction.equalsIgnoreCase("left")) {
            for (Tile tile : world.getTileList()) {
                if (tile.getCollisionBox().contains(actorCB.x - speed, actorCB.y)
                        || tile.getCollisionBox().contains(actorCB.x - speed, actorCB.y + actorCB.height)) {
                    if (tile.isSolid()) {
                        return true;
                    }
                }
            }
        }
        //TODO: fix so its not lokking for speed when checking beneath actor
        if (direction.equalsIgnoreCase("down")) {
            for (Tile tile : world.getTileList()) {
                if (tile.getCollisionBox().contains(actorCB.x, actorCB.y + actorCB.height + speed)
                        || tile.getCollisionBox().contains(actorCB.x + actorCB.width, actorCB.y + actorCB.height + speed)) {
                    if (tile.isSolid()) {
                        return true;
                    }
                }
            }
        }
        if(direction.equalsIgnoreCase("up")){
            for (Tile tile : world.getTileList()) {
                if (tile.getCollisionBox().contains(actorCB.x, actorCB.y - speed)
                        || tile.getCollisionBox().contains(actorCB.x + actorCB.width, actorCB.y - speed)) {
                    if (tile.isSolid()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
//    public Actor collisionWithActor(){
//        
//    }
}
