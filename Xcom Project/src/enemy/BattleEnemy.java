package enemy;

import ai.AI;
import world.World;

public abstract class BattleEnemy extends Enemy{
    
    protected boolean endTurn;

    public BattleEnemy(int x, int y, World world, String texturePath, int numOfAnimation, int numOfFrames, AI ai) {
        super(x, y, world, texturePath, numOfAnimation, numOfFrames, ai);
    }
    
    public abstract boolean endTurn();

    public void setEndTurn(boolean endTurn) {
        this.endTurn = endTurn;
    }
    
    
}
