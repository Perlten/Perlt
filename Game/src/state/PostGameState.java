package state;

import display.Display;
import game.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import keyinput.MouseInput;

public class PostGameState implements State {

    private Rectangle quitButton;
    private Rectangle restartButton;
    private Game game;
    private MouseInput mouse;

    public PostGameState(Game game) {
        this.game = game;
        this.mouse = game.getMouseInput();
        quitButton = new Rectangle(280, 150, 50, 50);
        restartButton = new Rectangle(180, 150, 75, 50);
    }

    @Override
    public void update() {
        if (quitButton.contains(mouse.getMouseX(), mouse.getMouseY()) && mouse.isLeftMouse()) {
            System.exit(0);
        }
        if (restartButton.contains(mouse.getMouseX(), mouse.getMouseY()) && mouse.isLeftMouse()) {
            StateManager.makeNewGameState(game);
            game.setStage(StateManager.gameState);
        }
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(0, 0, game.getDisplay().getWidth(), game.getDisplay().getHeight());

        
        g.setColor(Color.BLUE);
        g.fillRect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);
        g.fillRect(restartButton.x, restartButton.y, restartButton.width, restartButton.height);
        g.setColor(Color.ORANGE);
        g.drawString("Quit", quitButton.x + quitButton.width / 2 - 12, quitButton.y + quitButton.height / 2 + 2);
        g.drawString("Restart", restartButton.x + restartButton.width / 2 - 17, restartButton.y + restartButton.height / 2 + 2);
        
        g.setFont(new Font(null, 1, 15));
        g.drawString("YOU DIED!", 225, 75);
    }
}
