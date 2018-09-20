/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import game.Game;
import java.awt.Graphics;
import world.World;

/**
 *
 * @author Perlt
 */
public interface State {
    
    public void update();
    public void render(Graphics g);
    public String getStageChange();
    public World getWorld();
    public Game getGame();

}
