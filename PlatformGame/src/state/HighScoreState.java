package state;

import game.Game;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import menuItem.MenuItem;
import world.World;

public class HighScoreState implements State{

    private String nextState;
    private List<MenuItem> buttonList = new ArrayList<>();
    private Game game;

    public HighScoreState(Game game) {
        this.game = game;
    }
    
    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
    }

    @Override
    public String nextState() {
        return nextState;
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
