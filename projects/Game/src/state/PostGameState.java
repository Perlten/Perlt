package state;

import database.DataMapper;
import game.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Scanner;
import javax.swing.JTextField;
import keyinput.MouseInput;

public class PostGameState implements State {

    private Rectangle quitButton;
    private Rectangle restartButton;
    private Rectangle highscore;
    private Rectangle showHighscore;
    private Game game;
    private MouseInput mouse;

    private boolean scoreSubmit;

    public PostGameState(Game game) {
        this.game = game;
        this.mouse = game.getMouseInput();
        quitButton = new Rectangle(280, 150, 45, 50);
        restartButton = new Rectangle(180, 150, 70, 50);
        highscore = new Rectangle(150, 250, 100, 45);
        showHighscore = new Rectangle(280, 250, 125, 45);
        scoreSubmit = false;
    }

    @Override
    public void update() {
        if (quitButton.contains(mouse.getMouseX(), mouse.getMouseY()) && mouse.isLeftMouse()) {
            System.exit(0);
        }
        if (restartButton.contains(mouse.getMouseX(), mouse.getMouseY()) && mouse.isLeftMouse()) {
            StateManager.makeNewGameState(game);
            StateManager.makeNewPostGameState(game);
            game.setStage(StateManager.gameState);
        }
        if (highscore.contains(mouse.getMouseX(), mouse.getMouseY()) && mouse.isLeftMouse()) {
            System.out.println("Enter name");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.next();
            DataMapper dm = new DataMapper();
            dm.insertNewScore(name, StateManager.gameState.getPlayer().getScore());
            scoreSubmit = true;
        }
        if(showHighscore.contains(mouse.getMouseX(), mouse.getMouseY()) && mouse.isLeftMouse()){
            StateManager.makeNewGameState(game);
            game.setStage(new HighScoreState(game));
        }
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(0, 0, game.getDisplay().getWidth(), game.getDisplay().getHeight());

        g.setColor(Color.BLUE);
        g.fillRect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);
        g.fillRect(restartButton.x, restartButton.y, restartButton.width, restartButton.height);
        g.fillRect(showHighscore.x, showHighscore.y, showHighscore.width, showHighscore.height);
        if (!scoreSubmit) {
            g.fillRect(highscore.x, highscore.y, highscore.width, highscore.height);
        }

        g.setColor(Color.ORANGE);
        g.drawString("Quit", quitButton.x + quitButton.width / 2 - 12, quitButton.y + quitButton.height / 2 + 2);
        g.drawString("Restart", restartButton.x + restartButton.width / 2 - 17, restartButton.y + restartButton.height / 2 + 2);
        g.drawString("Show Highscore", showHighscore.x + showHighscore.width / 2 - 45, showHighscore.y + showHighscore.height / 2 + 2);
        if (!scoreSubmit) {
            g.drawString("Add Highscore", highscore.x + highscore.width / 2 - 40, highscore.y + highscore.height / 2 + 2);
        }

        g.setFont(new Font(null, 1, 15));
        g.drawString("YOU DIED!", 225, 75);
        g.drawString("Score: " + StateManager.gameState.getPlayer().getScore(), 225, 100);
    }
}
