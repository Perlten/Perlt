/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import actors.Player;
import game.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import menuItem.MenuItem;
import world.World;

public class EndGameState implements State {

    private String stateChange;
    private Game game;
    private List<MenuItem> menuItemList = new ArrayList<>();
    
    private int playerPoints;
    private int playerHealth;

    public EndGameState(Game game) {
        this.game = game;
        menuItemList.add(new MenuItem("resources/textures/newGame.png", "gameState", game.getMouseInput(), 110, 100));
        playerPoints = Player.points;
        playerHealth = Player.health;
        Player.health = 3;
        Player.points = 0;
        playerPoints += playerHealth * 10;
    }

    @Override
    public void update() {
        for (MenuItem item : menuItemList) {
            String string = item.clicked();
            if (string != null) {
                stateChange = string;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("", 0, 25));
        g.drawString("Total Points: " + String.valueOf(playerPoints), 115, 75);
        for (MenuItem item : menuItemList) {
            item.render(g);
        }
    }

    @Override
    public String nextState() {
        return stateChange;
    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public Game getGame() {
        return null;
    }

}
