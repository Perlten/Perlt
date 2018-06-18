package onlinegamecommen;

import java.io.Serializable;

public class PlayerPacket implements Serializable {

    private int x, y;

    public PlayerPacket(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
