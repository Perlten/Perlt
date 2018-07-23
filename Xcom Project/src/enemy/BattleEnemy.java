package enemy;

import actors.BattleObject;
import ai.AI;
import world.World;

public abstract class BattleEnemy extends Enemy implements BattleObject{
    
    protected boolean endTurn;

    public BattleEnemy(int x, int y, World world, String texturePath, int numOfAnimation, int numOfFrames, AI ai) {
        super(x, y, world, texturePath, numOfAnimation, numOfFrames, ai);
    }
    
    public void setEndTurn(boolean endTurn) {
        this.endTurn = endTurn;
    }
    
    
}
