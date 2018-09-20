/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;

/**
 *
 * @author Perlt
 */
public class EnemyWrapper implements Serializable {
    private int x, y;
    private int speed;
    private String path;

    public EnemyWrapper(int x, int y, int speed, String path) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.path = path;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public String getPath() {
        return path;
    }
    
    
}
