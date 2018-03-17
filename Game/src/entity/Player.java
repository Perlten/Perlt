package entity;

import collision.Collision;
import game.Game;
import java.awt.Color;
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
    private Rectangle cb;
    private World world;
    private Collision col;

    private int score;

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
        cb = new Rectangle(x + 23, y + 30, 25, 25);
        col = new Collision(this, world);
        score = 0;
    }

    public void update() {
        move();
        updateCollision();
    }

    public void render(Graphics g) {
        g.drawImage(texture, x, y, null);
        g.fillRect(cb.x, cb.y, cb.width, cb.height);
        g.setColor(Color.orange);
        g.drawString(String.valueOf(score), 10, 20);
    }

    private void move() {

        if (keyInput.isUp()) {
            if (col.checkCollisionWithTile("up")) {
                y -= 3;
            }
            if (col.checkCollisionWithStar("up")) {
                score++;
            }
        }
        if (keyInput.isDown()) {
            if (col.checkCollisionWithTile("down")) {
                y += 3;
            }
            if (col.checkCollisionWithStar("down")) {
                score++;
            }
        }
        if (keyInput.isLeft()) {
            if (col.checkCollisionWithTile("left")) {
                x -= 3;
            }
            if (col.checkCollisionWithStar("left")) {
                score++;
            }
        }
        if (keyInput.isRight()) {
            if (col.checkCollisionWithTile("right")) {
                x += 3;
            }
            if (col.checkCollisionWithStar("right")) {
                score++;
            }
        }
    }

    private void updateCollision() {
        cb.setBounds(x + 20, y + 30, 25, 25);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getCb() {
        return cb;
    }
}
