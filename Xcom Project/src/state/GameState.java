package state;

import camera.Camera;
import display.Display;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import util.TextureUtil;
import world.OverWorld;
import world.World;
import world.World0;

public class GameState implements State {

    private List<OverWorld> worldList = new ArrayList<>();
    private World currentWorld;

    private StateType stateType = StateType.VOID;
    
    public GameState(KeyInput keyInput, MouseInput mouseInput) {
        worldList.add(new World0(mouseInput, keyInput, this));
        this.currentWorld = worldList.get(0);
    }

    @Override
    public void update() {
        currentWorld.update();
    }

    @Override
    public void render(Graphics g) {
        if (currentWorld.isWorldloaded()) {
            g.translate(-Camera.xOffset, -Camera.yOffset);

            currentWorld.render(g);

            g.translate(Camera.xOffset, Camera.yOffset);
            //Draw fixed graphics here
        }
        currentWorld.renderLoadingScrenn(g);
    }

    @Override
    public StateType getStateType() {
        return stateType;
    }

    @Override
    public void changeState(StateType stateType) {
        this.stateType = stateType;
    }
}
