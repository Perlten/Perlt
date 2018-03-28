/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physics;

import actors.Actor;
import actors.Collision;

/**
 *
 * @author Perlt
 */
public class Gravity {

    private Actor actor;
    private double fallSpeed;
    private boolean falling;

    public Gravity(Actor actor) {
        this.actor = actor;
        fallSpeed = 0;
    }

    public void update() {
        falling();
    }

    public void falling() {
        Collision col = actor.getCollision();
        //TODO: there might be a problem with falling and getting stuck in a tile 
        if (!col.collisionWithSolidTile((int) (fallSpeed + 1), "down")) {
            fallSpeed += 0.3;
            actor.addToY((int) fallSpeed);
            falling = true;
        }else{
            falling = false;
            fallSpeed = 0;
        }
    }

    public boolean isFalling() {
        return falling;
    }
}
