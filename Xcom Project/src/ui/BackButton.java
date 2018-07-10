package ui;

import input.MouseInput;
import state.State;
import state.StateType;

public class BackButton extends Button {
    
    private StateType stateType;
    private State state;

    public BackButton(int x, int y, MouseInput mouse, StateType stateType, State state) {
        super(x, y, "back.png", mouse);
        this.stateType = stateType;
        this.state = state;
    }

    @Override
    public void init() {
    }

    @Override
    public void onInteract() {
        if(hitBoxContains()){
            state.changeState(stateType);
        }
    }

}
