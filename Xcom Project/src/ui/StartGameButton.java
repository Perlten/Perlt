package ui;

import input.MouseInput;
import state.State;
import state.StateType;

public class StartGameButton extends Button{
    
    private State state;

    public StartGameButton(int x, int y, State state, MouseInput mouseInput) {
        super(x, y, "start.png", mouseInput);
        this.state = state;
    }

    @Override
    public void init() {

    }

    @Override
    public void onInteract() {
        if(hitbox.contains(mouse.getX(), mouse.getY()) && mouse.isLeftMouse()){
            state.changeState(StateType.GAMESTATE);
        }
    }

}
