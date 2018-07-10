package state;

import java.awt.Graphics;

public class OptionState implements  State{
    
    private StateType currentState = StateType.VOID;

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
    }

    @Override
    public void changeState(StateType stateType) {
        this.currentState = stateType;
    }

    @Override
    public StateType getStateType() {
        return currentState;
    }

}
