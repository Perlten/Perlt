package actors;

public interface BattleObject {
    
    public boolean isTurnOver();
    public void checkDeath();
    
    public void battleStart();
    public void battleEnd();
    
}
