package ui;

import input.MouseInput;
import state.State;
import state.StateType;

public class StateChangeButton extends Button{
    
    private State state;
    private StateType stateType;
    
    public StateChangeButton(int x, int y, State state, MouseInput mouseInput, StateType stateType, String texture) {
        super(x, y, texture, mouseInput);
        this.state = state;
        this.stateType = stateType;
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
