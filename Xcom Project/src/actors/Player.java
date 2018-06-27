package actors;

import input.KeyInput;
import java.awt.Graphics;
import java.awt.Rectangle;
import physics.Collision;

public class Player extends Actor {

    private KeyInput keyInput;

    public Player(int x, int y, String texturePath, KeyInput keyInput, int movementSpeed, Collision collision) {
        super(x, y, new Rectangle(32, 32), texturePath, movementSpeed, collision);
        this.keyInput = keyInput;
    }

    @Override
    public void update() {
        updateHitbox();
        movement();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture, x, y, null);
        g.fillRect(x, y, hitbox.width, hitbox.height);
    }

    private void movement() {
        if (keyInput.isUp() && !collision.isPlayerCollisionUp()) {
            y -= movementSpeed;
        } else if (keyInput.isDown() && !collision.isPlayerCollisionDown()) {
            y += movementSpeed;
        } else if (keyInput.isLeft() && !collision.isPlayerCollisionLeft()) {
            x -= movementSpeed;
        } else if (keyInput.isRight() && !collision.isPlayerCollisionRight()) {
            x += movementSpeed;
        }
    }

    public void setCollision(Collision collision) {
        this.collision = collision;
    }

}
