/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Graphics;
import java.io.Serializable;

/**
 *
 * @author Perlt
 */
public class FpsManager implements Serializable{
    
    private double timesPerUpdate;
    private double delta;
    private long now;
    private long last = System.nanoTime();
    
    private long timer;
    private int tick;
    
    
    public FpsManager(int fps) {
        this.timesPerUpdate = 1e9 / fps;
    }
    
    public boolean check(){
        now = System.nanoTime();
        delta += (now - last) / timesPerUpdate;
        timer += now - last;
        last = now;
        
        if(delta >= 1){
            tick++;
            delta = 0;
            return true;
        }
        return false;
    }
    
    public void printFps(){
        if(timer >= 1e9){
            System.out.println("FPS: " + tick);
            tick = 0;
            timer = 0;
        }
    }
}
