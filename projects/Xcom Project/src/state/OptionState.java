package state;

import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import ui.BackButton;
import ui.Button;
import ui.TextInput;

public class OptionState implements  State{
    
    private StateType currentState = StateType.VOID;
    private List<Button> buttonList = new ArrayList<>();
    private TextInput textInput;
    
    public OptionState(MouseInput mouse, KeyInput key) {
        buttonList.add(new BackButton(200, 200, mouse, StateType.MENUSTATE, this));
        this.textInput = new TextInput(500, 500, 200, key, mouse);
    }
    
    @Override
    public void update() {
        for (Button button : buttonList) {
            button.onInteract();
        }
        textInput.onInteract();
    }

    @Override
    public void render(Graphics g) {
        for (Button button : buttonList) {
            button.render(g);
        }
        textInput.render(g);
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
