package state;

import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import world.BattleWorld1;
import world.World;

public class BattleState implements State {

    private StateType stateType = StateType.VOID;
    private World world;

    public BattleState(MouseInput mouseInput, KeyInput keyInput) {
        this.world = new BattleWorld1(mouseInput, keyInput, this);
    }

    @Override
    public void update() {
        world.update();
    }

    @Override
    public void render(Graphics g) {
        if (world.isWorldloaded()) {
            world.render(g);
        }
        world.renderLoadingScrenn(g);
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
