package state;

import camera.Camera;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import world.World;
import world.World0;

public class GameState implements State {

    private List<World> worldList = new ArrayList<>();
    private World currentWorld;
    
    public GameState(KeyInput keyInput, MouseInput mouseInput) {
        worldList.add(new World0(mouseInput, keyInput));
        this.currentWorld = worldList.get(0);
    }

    @Override
    public void update() {
        currentWorld.update();
    }

    @Override
    public void render(Graphics g) {
        g.translate(-Camera.xOffset, -Camera.yOffset);
        currentWorld.render(g);
        g.translate(Camera.xOffset, Camera.yOffset);
        //Draw fixed graphics here
        g.drawRect(0, 0, 32, 32);
    }

    @Override
    public StateType getStateType() {
        if(currentWorld.getKey().isLevelChangeTest()){
            return StateType.MENUSTATE;
        }
        return StateType.VOID;
    }

    @Override
    public void changeState(StateType stateType) {

    }
}
