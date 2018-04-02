package state;

import database.DataMapper;
import database.ScoreEntity;
import game.Game;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import menuItem.MenuItem;
import world.World;

public class HighScoreState implements State{

    private String stateChange;
    private List<MenuItem> buttonList = new ArrayList<>();
    private Game game;
    private List<ScoreEntity> scoreList;
    
    public HighScoreState(Game game) {
        this.game = game;
        buttonList.add(new MenuItem("resources/textures/backButton.png", "mainMenuState", game.getMouseInput(), 25, 225));
        scoreList = new DataMapper().getSortedScoreList();
    }
    
    @Override
    public void update() {
        for(MenuItem item : buttonList){
            String nextState = item.clicked();
            if(nextState != null){
                stateChange = nextState;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        
        for(MenuItem item : buttonList){
            item.render(g);
        }
        
        if(scoreList.size() >= 10){
            for (int i = 0; i < 10; i++) {
                g.drawString(scoreList.get(i).toString(), 100, (i * 25) + 40);
            }
        }else{
            for(int i = 0; i < scoreList.size(); i++){
                g.drawString(scoreList.get(i).toString(), 100, (i * 25) + 40);
            }
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
