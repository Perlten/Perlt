package state;

import game.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import keyinput.MouseInput;

/**
 *
 * @author Perlt
 */
public class PreGameState implements State {

    private Game game;
    private MouseInput mouse;
    
    private Rectangle startButton;
    private Rectangle hightScoreButton;
    
    public PreGameState(Game game) {
        this.game = game;
        mouse = game.getMouseInput();
        startButton = new Rectangle(180, 150, 50, 50);
        hightScoreButton = new Rectangle(280, 150, 75, 50);
    }
    
    @Override
    public void update() {
        if(startButton.contains(mouse.getMouseX(), mouse.getMouseY()) && mouse.isLeftMouse()){
            game.setStage(StateManager.gameState);
        }
        if(hightScoreButton.contains(mouse.getMouseX(), mouse.getMouseY()) && mouse.isLeftMouse()){
            game.setStage(new HighScoreState(game));
        }
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(0, 0, game.getDisplay().getWidth(), game.getDisplay().getHeight());

        
        g.setColor(Color.BLUE);
        g.fillRect(startButton.x, startButton.y, startButton.width, startButton.height);
        g.fillRect(hightScoreButton.x, hightScoreButton.y, hightScoreButton.width, hightScoreButton.height);
        g.setColor(Color.ORANGE);
        g.drawString("Start", startButton.x + startButton.width / 2 - 12, startButton.y + startButton.height / 2 + 2);
        g.drawString("highscore", hightScoreButton.x + hightScoreButton.width / 2 - 25, hightScoreButton.y + hightScoreButton.height / 2 + 2);
        
        g.setFont(new Font(null, 1, 15));
        g.drawString("NEW GAME!", 225, 75);
    }
}
