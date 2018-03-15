package entity;

import game.Game;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import keyinput.KeyInput;


public class Player {

    private BufferedImage texture;
    private int x, y;
    private Game game;
    private KeyInput keyInput;
    private Rectangle collisionBox;
    

    public Player(String path, int x, int y, Game game) {
        try {
            texture = ImageIO.read(new File(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.x = x;
        this.y = y;
        this.game = game;
        this.keyInput = game.getKeyInput();
        collisionBox = new Rectangle(x + 20, y + 30, 25, 25);
    }
    
    public void update(){
        move();
        updateCollision();
    }
    
    public void render(Graphics g){
        g.drawImage(texture, x, y, null);
        g.fillRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
    }
    
    private void move(){
        if(keyInput.isUp()){
            y -= 3;
        }
        if(keyInput.isDown()){
            y += 3;
        }
        if(keyInput.isLeft()){
            x -= 3;
        }
        if(keyInput.isRight()){
            x += 3;
        }
    }
    
    private void updateCollision(){
        collisionBox.setBounds(x + 20, y + 30, 25, 25);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}
