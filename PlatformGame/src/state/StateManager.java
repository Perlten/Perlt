/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import game.Game;

/**
 *
 * @author Perlt
 */
public class StateManager {

    public static State getState(String name, Game game) {
        if (name.equalsIgnoreCase("gamestate")) {
            return new GameState(game);
        }
        if (name.equalsIgnoreCase("mainmenustate")) {
            return new MainMenuState(game);
        }
        if (name.equalsIgnoreCase("endgameState")) {
            return new EndGameState(game);
        }
        return null;
    }

}
