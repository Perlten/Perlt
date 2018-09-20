/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Perlt
 */
public class ScoreEntity implements Comparable<ScoreEntity>{
    
    private String name;
    private int score;
    private int id;

    public ScoreEntity(String name, int score, int id) {
        this.name = name;
        this.score = score;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Score: " + score;
    }

    @Override
    public int compareTo(ScoreEntity se) {
        return se.score - this.score;   
    }
    
    
    
}
