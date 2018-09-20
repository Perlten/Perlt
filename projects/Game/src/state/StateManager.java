package state;

import game.Game;

/**
 *
 * @author Perlt
 */
public class StateManager {
    
    public static GameState gameState;
    public static PostGameState postGameState;
    public static PreGameState preGameState;
    
    public static void initStates(Game game){
        gameState = new GameState(game);
        postGameState = new PostGameState(game);
        preGameState = new PreGameState(game);
    }
    
    public static void makeNewGameState(Game game){
        gameState = new GameState(game);
    }
    
    public static void makeNewPostGameState(Game game){
        postGameState = new PostGameState(game);
    }
}
