package state;

import database.DataMapper;
import database.ScoreEntity;
import game.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import keyinput.MouseInput;

/**
 *
 * @author Perlt
 */
public class HighScoreState implements State {

    private List<ScoreEntity> list;
    private Game game;
    private MouseInput mouse;
    private Rectangle backButton;

    public HighScoreState(Game game) {
        this.game = game;
        mouse = game.getMouseInput();
        DataMapper dm = new DataMapper();
        list = dm.getSortedScoreList();
        backButton = new Rectangle(10, game.getDisplay().getHeight() - 50, 50, 50);
    }

    @Override
    public void update() {
        if(backButton.contains(mouse.getMouseX(), mouse.getMouseY()) && mouse.isLeftMouse()){
            game.setStage(StateManager.preGameState);
        }
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(0, 0, game.getDisplay().getWidth(), game.getDisplay().getHeight());
        g.setColor(Color.yellow);
        if (list.size() >= 10) {
            for (int i = 0; i < 10; i++) {
                g.drawString(String.valueOf(list.get(i)), 190, 35 * (i + 1));
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                g.drawString(String.valueOf(list.get(i)), 150, 35 * (i + 1));
            }
        }
        g.setColor(Color.BLUE);
        g.fillRect(backButton.x, backButton.y, backButton.width, backButton.height);
        g.setColor(Color.ORANGE);
        g.drawString("Back", backButton.x + backButton.width / 2 - 12, backButton.y + backButton.height / 2 + 2);
    }
}
