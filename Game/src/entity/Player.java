package entity;

import collision.Collision;
import game.Game;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import keyinput.KeyInput;
import world.World;

public class Player {

    private BufferedImage texture;
    private int x, y;
    private Game game;
    private KeyInput keyInput;
    private Rectangle collisionBox;
    private World world;
    private Collision col;

    public Player(String path, int x, int y, Game game, World world) {
        try {
            texture = ImageIO.read(new File(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.x = x;
        this.y = y;
        this.game = game;
        this.world = world;
        this.keyInput = game.getKeyInput();
        collisionBox = new Rectangle(x + 20, y + 30, 25, 25);
        col = new Collision(this, world);
    }

    public void update() {
        move();
        updateCollision();
    }

    public void render(Graphics g) {
        g.drawImage(texture, x, y, null);
        g.fillRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
    }

    private void move() {

        if (keyInput.isUp()) {
            if (col.isColliding(collisionBox.x, collisionBox.y - 3) && col.isColliding(collisionBox.x + collisionBox.width, collisionBox.y - 3)) {
                y -= 3;
            }
        }
        if (keyInput.isDown()) {
            if (col.isColliding(collisionBox.x, collisionBox.y + collisionBox.height + 3) && col.isColliding(collisionBox.x + collisionBox.width, collisionBox.y + collisionBox.height + 3)) {
                y += 3;
            }
        }
        if (keyInput.isLeft()) {
            if (col.isColliding(collisionBox.x - 3, collisionBox.y) && col.isColliding(collisionBox.x, collisionBox.y + collisionBox.height - 3)) {
                x -= 3;
            }
        }
        if (keyInput.isRight()) {
            if(col.isColliding(collisionBox.x + collisionBox.width + 3, collisionBox.y) && col.isColliding(collisionBox.x + collisionBox.width + 3, collisionBox.y + collisionBox.height)){
                x += 3;
            }
        }
    }

    private void updateCollision() {
        collisionBox.setBounds(x + 20, y + 30, 25, 25);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
