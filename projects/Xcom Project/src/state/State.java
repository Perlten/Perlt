package state;

import java.awt.Graphics;

public interface State {


    public void update();
    public void render(Graphics g);
    public void changeState(StateType stateType);
    public StateType getStateType();
    
}