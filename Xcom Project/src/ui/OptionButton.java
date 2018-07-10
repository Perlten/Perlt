package ui;

import input.MouseInput;
import state.State;
import state.StateType;

public class OptionButton extends Button{
    
    private State state;

    public OptionButton(int x, int y, State state, MouseInput mouse) {
        super(x, y, "option.png", mouse);
        this.state = state;
    }


    @Override
    public void init() {
    }

    @Override
    public void onInteract() {
        if(hitbox.contains(mouse.getX(), mouse.getY()) && mouse.isLeftMouse()){
            state.changeState(StateType.OPTION);
        }
    }

}
