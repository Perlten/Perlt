package state;

import camera.Camera;
import enemy.Enemy;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import world.OverWorld;
import world.World;
import world.World0;

public class GameState implements State {
    
    public static Enemy lastEnemyToFight;

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
        //removeEnemy() MUST come after currentWorld.update()
        removeEnemy();
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
    
    private void removeEnemy(){
        //Removes enemy from world when player enters a fight
         if(lastEnemyToFight != null && !currentWorld.getMapEditor().edit){
            currentWorld.getEnemyList().remove(lastEnemyToFight);
            currentWorld.getMapEditor().saveWorld();
            lastEnemyToFight = null;
        }
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
