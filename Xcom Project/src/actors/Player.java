package actors;

import input.KeyInput;
import java.awt.Graphics;

public class Player extends Actor {

    private KeyInput keyInput;

    public Player(int x, int y, String texturePath, KeyInput keyInput, int movementSpeed) {
        super(x, y, true, texturePath, movementSpeed);
        this.keyInput = keyInput;
    }

    @Override
    public void update() {
        movement();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture, x, y, null);
    }

    private void movement() {
        if (keyInput.isUp()) {
            y -= movementSpeed;
        } else if (keyInput.isDown()) {
            y += movementSpeed;
        } else if (keyInput.isLeft()) {
            x -= movementSpeed;
        } else if (keyInput.isRight()) {
            x += movementSpeed;
        }
    }

}
