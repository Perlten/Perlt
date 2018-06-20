package player;

import input.KeyInput;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Host implements Player {

    private int x, y;
    private KeyInput keyInput;
    private Rectangle hitbox = new Rectangle(x, y, 25, 25);
    
    public Host(KeyInput keyInput) {
        this.keyInput = keyInput;
    }

    @Override
    public void update() {
        if (keyInput.isDown()) {
            y += 3;
        }
        if (keyInput.isUp()) {
            y -= 3;
        }
        if (keyInput.isLeft()) {
            x -= 3;
        }
        if (keyInput.isRight()) {
            x += 3;
        }
        hitbox.setLocation(x, y);
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(x, y, 25, 25);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
