package actors;

public interface BattleObject {
    
    public boolean isTurnOver();
    public void checkDeath();
    
    
    public void roundStart();
    public void roundEnd();
    public void battleStart();
    public void battleEnd();
    
}
