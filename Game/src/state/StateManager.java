package state;

import game.Game;

/**
 *
 * @author Perlt
 */
public class StateManager {
    
    public static GameState gameState;
    public static PostGameState postGameState;
    
    public static void initStates(Game game){
        gameState = new GameState(game);
        postGameState = new PostGameState(game);
    }
    
    public static void makeNewGameState(Game game){
        gameState = new GameState(game);
    }
}
