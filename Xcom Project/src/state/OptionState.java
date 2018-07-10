package state;

import input.MouseInput;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import ui.BackButton;
import ui.Button;

public class OptionState implements  State{
    
    private StateType currentState = StateType.VOID;
    private List<Button> buttonList = new ArrayList<>();

    public OptionState(MouseInput mouse) {
        buttonList.add(new BackButton(200, 200, mouse, StateType.MENUSTATE, this));
    }
    
    @Override
    public void update() {
        for (Button button : buttonList) {
            button.onInteract();
        }
    }

    @Override
    public void render(Graphics g) {
        for (Button button : buttonList) {
            button.render(g);
        }
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
