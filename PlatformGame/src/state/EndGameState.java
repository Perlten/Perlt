/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import actors.Player;
import database.DataMapper;
import game.Game;
import input.KeyInput;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import menuItem.Button;
import menuItem.MenuItem;
import world.World;

public class EndGameState implements State {

    private String stateChange;
    private Game game;
    private List<MenuItem> menuItemList = new ArrayList<>();
    private Button submitButton;

    private KeyInput key;

    private int playerPoints;
    private int playerHealth;

    public EndGameState(Game game) {
        this.game = game;
        key = game.getKeyInput();
        menuItemList.add(new MenuItem("resources/textures/newGame.png", "gameState", game.getMouseInput(), 110, 80));
        menuItemList.add(new MenuItem("resources/textures/highScore.png", "highScoreState", game.getMouseInput(), 100, 155));
        submitButton = new Button("resources/textures/submitButton.png", 125, 250, game.getMouseInput());
        playerPoints = Player.points;
        playerHealth = Player.health;
        Player.health = 3;
        Player.points = 0;
        playerPoints += playerHealth * 10;
    }

    @Override
    public void update() {
        if (submitButton.isClicked()) {
            submitScore();
            submitButton.setClickAble(false);
            submitButton.setRender(false);
        }
        for (MenuItem item : menuItemList) {
            String string = item.clicked();
            if (string != null) {
                stateChange = string;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
        submitButton.render(g);
        g.setColor(Color.red);
        g.setFont(new Font("", 0, 25));
        g.drawString("Total Points: " + String.valueOf(playerPoints), 115, 75);
        for (MenuItem item : menuItemList) {
            item.render(g);
        }
    }

    private void submitScore() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Name");
        String name = sc.next();
        DataMapper dm = new DataMapper();
        dm.insertScore(name, playerPoints);
    }

    @Override
    public String getStageChange() {
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
