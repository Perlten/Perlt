package state;

import actors.BattlePlayer;
import enemy.BattleEnemy;
import enemy.Enemy;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import world.BattleWorld1;
import world.World;

public class BattleState implements State {

    private StateType stateType = StateType.VOID;
    private World world;

    private boolean playerTurn = true;

    public BattleState(MouseInput mouseInput, KeyInput keyInput) {
        this.world = new BattleWorld1(mouseInput, keyInput, this);
    }

    @Override
    public void update() {
        world.update();

        //Changes turn
        if (playerTurn) {
            BattlePlayer p = (BattlePlayer) world.getPlayer();
            playerTurn = p.isTurnOver();
            world.getPlayer().update();
        } else {
            for (Enemy enemy : world.getEnemyList()) {
                BattleEnemy be = (BattleEnemy) enemy;
                enemy.update();
                playerTurn = be.endTurn();
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
