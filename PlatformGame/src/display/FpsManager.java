/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Graphics;

/**
 *
 * @author Perlt
 */
public class FpsManager {
    
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
            delta--;
            return true;
        }
        return false;
    }
    
    public void printFps(Graphics g){
        if(timer >= 1e9){
            System.out.println(tick);
            tick = 0;
            timer = 0;
        }
    }
}
