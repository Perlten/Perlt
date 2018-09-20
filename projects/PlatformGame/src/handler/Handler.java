/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import actors.Actor;
import input.KeyInput;
import input.MouseInput;
import state.State;
/**
 *
 * @author Perlt
 */
public class Handler {
    
    private KeyInput keyInput;
    private MouseInput mouseInput;
    private State state;
    private Actor actor;
    
    public Handler(KeyInput keyInput, MouseInput mouseInput, State state) {
        this.keyInput = keyInput;
        this.mouseInput = mouseInput;
        this.state = state;
    }

    public KeyInput getKeyInput() {
        return keyInput;
    }

    public MouseInput getMouseInput() {
        return mouseInput;
    }

    public State getState() {
        return state;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor player) {
        this.actor = player;
    }
}
