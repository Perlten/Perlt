package enemy;

import actors.BattleObject;
import ai.AI;
import world.World;

public abstract class BattleEnemy extends Enemy implements BattleObject{
    
    protected boolean endTurn;
    protected int ap;
    protected int maxAp;
    protected int health;
    protected int maxHealth;

    public BattleEnemy(int x, int y, World world, String texturePath, int numOfAnimation, int numOfFrames, AI ai, int ap, int movementSpeed, int MaxHealth) {
        super(x, y, world, texturePath, numOfAnimation, numOfFrames, ai, movementSpeed);
        this.ap = ap;
        this.maxAp = ap;
        this.maxHealth = MaxHealth;
        this.health = MaxHealth;
    }
    
    public abstract void onDeath();
    
    public void setEndTurn(boolean endTurn) {
        this.endTurn = endTurn;
    }

    public int getAp() {
        return ap;
    }

    public int getMaxAp() {
        return maxAp;
    }

    public void changeAp(int ap) {
        this.ap += ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }
    
    public void chnageHealth(int change){
        this.health += change;
    }
}
