/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import actors.Actor;
import game.Game;


public class Camera {
    
    public static int xOffset;
    private Game game;

    public Camera(Game game) {
        this.game = game;
    }
    
    public void focusOnActor(Actor actor){
        xOffset = actor.getX() - game.getWidth() / 2 + actor.getTexture().getWidth() / 2;
    }

    public int getxOffset() {
        return xOffset;
    }
}
