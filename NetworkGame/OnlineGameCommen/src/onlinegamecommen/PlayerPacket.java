package onlinegamecommen;

import java.io.Serializable;
import java.security.SecureRandom;

public class PlayerPacket implements Serializable {

    private int x, y;
    private long id;


    public PlayerPacket(int x, int y) {
        this.x = x;
        this.y = y;
        id = new SecureRandom().nextLong();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public long getId() {
        return id;
    }
}
