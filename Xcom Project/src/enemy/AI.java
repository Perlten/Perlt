package enemy;

import java.awt.Point;

public interface AI {

    public Point move();

    public void reset();

    public void playerSeen();
}
