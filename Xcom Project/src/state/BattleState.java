package state;

import actors.BattlePlayer;
import enemy.BattleEnemy;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import world.BattleWorld;
import world.BattleWorld1;

public class BattleState implements State {

    private StateType stateType = StateType.VOID;
    private BattleWorld world;

    private boolean playerTurn = true;

    public BattleState(MouseInput mouseInput, KeyInput keyInput) {
        this.world = new BattleWorld1(mouseInput, keyInput, this);
    }

    @Override
    public void update() {
        world.update();

        //Changes turn
        if (playerTurn) {
            BattlePlayer p =  world.getBattlePlayer();
            playerTurn = p.isTurnOver();
            p.update();
        } else {
            if(world.getEnemyList().isEmpty()){
                changeState(StateType.GAMESTATE);
                return;
            }
            for (BattleEnemy enemy : world.getBattleEnemyList()) {
                enemy.update();
                playerTurn = enemy.isTurnOver();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (world.isWorldloaded()) {
            world.render(g);
            g.drawString("Player turn: " + playerTurn, 0, 30);
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
