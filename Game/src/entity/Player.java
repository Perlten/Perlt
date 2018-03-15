package entity;

import game.Game;
import java.awt.Graphics;
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
    }
    
    public void update(){
        move();
    }
    
    public void render(Graphics g){
        g.drawImage(texture, x, y, null);
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
}
