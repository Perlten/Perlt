package game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import display.Display;

/**
 *
 * @author Perlt
 */
public class Launcher {

    
    public static void main(String[] args) {
        Game game = new Game(550, 400, "lol");
        game.start();
    }
    
}
