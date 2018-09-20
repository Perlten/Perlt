package entity;

import collision.Collision;
import game.Game;
import java.awt.Color;
import java.awt.Font;
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
    
    private Font scoreFont;
    private Font normalFont;
    
    private int score;
    private int speed;

    public Player(String path, int x, int y, int speed, Game game, World world) {
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
        this.speed = speed;
        cb = new Rectangle(x + 23, y + 30, 25, 25);
        col = new Collision(cb, world);
        score = 0;
        scoreFont = new Font("Dialog", 1, 35);
        normalFont = new Font("roman", 0, 12);
    }

    public void update() {
        move();
        updateCollision();
    }

    public void render(Graphics g) {
        g.drawImage(texture, x, y, null);
//        g.fillRect(cb.x, cb.y, cb.width, cb.height); //Renders collisionBox
        g.setColor(Color.orange);
        g.setFont(scoreFont);
        g.drawString(String.valueOf(score), 8, 30);
        g.setFont(normalFont);
        
    }

    private void move() {

        if (keyInput.isUp()) {
            if (col.checkCollisionWithTile("up", speed)) {
                y -= speed;
            }
            if (col.checkCollisionWithStar("up", speed)) {
                score++;
            }
        }
        if (keyInput.isDown()) {
            if (col.checkCollisionWithTile("down", speed)) {
                y += speed;
            }
            if (col.checkCollisionWithStar("down", speed)) {
                score++;
            }
        }
        if (keyInput.isLeft()) {
            if (col.checkCollisionWithTile("left", speed)) {
                x -= speed;
            }
            if (col.checkCollisionWithStar("left", speed)) {
                score++;
            }
        }
        if (keyInput.isRight()) {
            if (col.checkCollisionWithTile("right", speed)) {
                x += speed;
            }
            if (col.checkCollisionWithStar("right", speed)) {
                score++;
            }
        }
    }

    public void updateSpeed(int amount){
        speed += amount;
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

    public BufferedImage getTexture() {
        return texture;
    }

    public int getScore() {
        return score;
    }
    
}
