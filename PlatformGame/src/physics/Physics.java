/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physics;

import actors.Actor;

/**
 *
 * @author Perlt
 */
public class Physics {

    private Actor actor;
    private Gravity gravity;

    public Physics(Actor actor) {
        this.actor = actor;
        gravity = new Gravity(actor);
    }

    public void update(boolean jumping) {
        if (!actor.getHandler().getKeyInput().isEditor()) {
            if (!jumping) {
                gravity.update();
            }
        }
    }

    public Gravity getGravity() {
        return gravity;
    }
}
