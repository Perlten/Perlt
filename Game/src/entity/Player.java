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
            if (col.isColliding(cb.x, cb.y - 3) && col.isColliding(cb.x + cb.width, cb.y - 3)) {
                y -= 3;
            }
             if (col.isTouchingStar(cb.x, cb.y - 3) || col.isTouchingStar(cb.x + cb.width, cb.y - 3)){
                 score++;
             }
        }
        if (keyInput.isDown()) {
            if (col.isColliding(cb.x, cb.y + cb.height + 3) && col.isColliding(cb.x + cb.width, cb.y + cb.height + 3)) {
                y += 3;
            }
             if (col.isTouchingStar(cb.x, cb.y + cb.height + 3) || col.isTouchingStar(cb.x + cb.width, cb.y + cb.height + 3)){
                 score ++;
             }
        }
        if (keyInput.isLeft()) {
            if (col.isColliding(cb.x - 3, cb.y) && col.isColliding(cb.x - 3, cb.y + cb.height)) {
                x -= 3;
            }
             if (col.isTouchingStar(cb.x - 3, cb.y) || col.isTouchingStar(cb.x - 3, cb.y + cb.height)){
                 score++;
             }
        }
        if (keyInput.isRight()) {
            if(col.isColliding(cb.x + cb.width + 3, cb.y) && col.isColliding(cb.x + cb.width + 3, cb.y + cb.height)){
                x += 3;
            }
            if(col.isTouchingStar(cb.x + cb.width + 3, cb.y) || col.isTouchingStar(cb.x + cb.width + 3, cb.y + cb.height)){
                score ++;
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

}
