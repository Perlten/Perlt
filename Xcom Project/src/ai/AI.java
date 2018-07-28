package ai;

import enemy.Enemy;
import java.awt.Point;

public interface AI {

    public Point move();

    public void reset();

    public void playerSeen();
    
    public void setEnemy(Enemy enemy);
}
