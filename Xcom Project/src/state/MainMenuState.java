package state;

import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import ui.Button;
import ui.OptionButton;
import ui.StartGameButton;
import world.MenuWorld;
import world.World;

public class MainMenuState implements State{

    private StateType currentStateType = StateType.VOID;
    private World world;
    
    private List<Button> buttonList = new ArrayList<>();

    public MainMenuState(MouseInput mouseInput, KeyInput keyInput) {
        this.world = new MenuWorld(mouseInput, keyInput);
        buttonList.add(new StartGameButton(475, 100, this, mouseInput));
        buttonList.add(new OptionButton(435, 275, this, mouseInput));
    }

    @Override
    public void update() {
        world.update();
        for (Button button : buttonList) {
            button.onInteract();
        }
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        for (Button button : buttonList) {
            button.render(g);
        }
    }

    @Override
    public StateType getStateType() {
        return currentStateType;
    }

    @Override
    public void changeState(StateType stateType) {
        this.currentStateType = stateType;
    }
    

}
