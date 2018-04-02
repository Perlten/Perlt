package state;

import game.Game;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import menuItem.MenuItem;
import world.World;

public class MainMenuState implements State {
    
    private String stateChange;
    private Game game;
    private List<MenuItem> menuItemList = new ArrayList<>();

    public MainMenuState(Game game) {
        this.game = game;
        menuItemList.add(new MenuItem("resources/textures/startButton.png", "gameState", game.getMouseInput(), 110, 100));
        menuItemList.add(new MenuItem("resources/textures/startButton.png", "highScoreState", game.getMouseInput(), 110, 150));
    }
    
    @Override
    public void update() {
      for(MenuItem item : menuItemList){
          String nextState = item.clicked();
            if(nextState != null){
            stateChange = nextState;
        }
      }
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
        for(MenuItem item : menuItemList){
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
