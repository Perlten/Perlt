package state;

import display.Display;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import ui.Button;
import ui.ExitButton;
import ui.StateChangeButton;
import world.MenuWorld;
import world.World;

public class MainMenuState implements State {

    private StateType currentStateType = StateType.VOID;
    private World world;

    private List<Button> buttonList = new ArrayList<>();

    public MainMenuState(MouseInput mouseInput, KeyInput keyInput) {
        this.world = new MenuWorld(mouseInput, keyInput, this);
        buttonList.add(new StateChangeButton(475, 100, this, mouseInput, StateType.GAMESTATE, "start.png"));
        buttonList.add(new StateChangeButton(435, 275, this, mouseInput, StateType.OPTION, "option.png"));
        buttonList.add(new ExitButton(0, Display.height - 50, "exit.png", mouseInput));
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
        if (world.isWorldloaded()) {
            world.render(g);
            for (Button button : buttonList) {
                button.render(g);
            }
        }
        world.renderLoadingScrenn(g);

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
