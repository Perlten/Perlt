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
        battleStart();
    }

    private void battleStart() {
        BattlePlayer player = world.getBattlePlayer();
        player.battleStart();

        for (BattleEnemy battleEnemy : world.getBattleEnemyList()) {
            battleEnemy.battleStart();
        }
    }

    @Override
    public void update() {
        world.update();
        BattlePlayer player = world.getBattlePlayer();

        //Checks if all enemys are dead
        if (world.getEnemyList().isEmpty()) {
            changeState(StateType.GAMESTATE);
            world.getBattlePlayer().battleEnd();
            for (BattleEnemy battleEnemy : world.getBattleEnemyList()) {
                battleEnemy.battleEnd();
            }
            return;
        }
        //Player turn
         if (playerTurn) {
            playerTurn = player.isTurnOver();
            //checks if player turn is over
            if (!playerTurn) {
                player.roundEnd();
                for (BattleEnemy battleEnemy : world.getBattleEnemyList()) {
                    battleEnemy.roundStart();
                }
                return;
            }
            player.update();
        } else {
            //Enemy turn
            boolean enemyTurnEnd = true;
            for (BattleEnemy enemy : world.getBattleEnemyList()) {
                enemy.update();
                if (!enemy.isTurnOver()) {
                    enemyTurnEnd = false;
                }
            }
            //Checks if enemy turn is ove
            if (enemyTurnEnd) {
                for (BattleEnemy battleEnemy : world.getBattleEnemyList()) {
                    battleEnemy.roundEnd();
                }
                player.roundStart();
            }
            playerTurn = enemyTurnEnd;
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
