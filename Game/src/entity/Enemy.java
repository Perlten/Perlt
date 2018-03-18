package entity;

import collision.Collision;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import state.GameState;
import state.StateManager;
import world.World;

public class Enemy {

    private BufferedImage texture;
    private int x, y;
    private int speed;
    
    private Rectangle cb;
    private Collision col;

    private Player player;
    private World world;
    private GameState state;
    
    public Enemy(GameState state, int x, int y, int speed) {
        try {
            texture = ImageIO.read(new File("resources/textures/enemy.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.state = state;
        
        player = state.getPlayer();
        world = state.getWorld();
        
        cb = new Rectangle(x + texture.getWidth()/2, y + texture.getHeight()/2, 22, 22);
        col = new Collision(cb, world);
    }

    public void update() {
        updateCollision();
        move();
        updateCollisionWithPlayer();
    }

    public void render(Graphics g) {
        g.drawImage(texture, x, y, null);
//        g.fillRect(cb.x, cb.y, cb.width, cb.height); //Renders the collision box
    }

    private void move() {
        if (player.getCb().y < cb.y) {
            if (col.checkCollisionWithTile("up")) {
                y -= speed;
            }
            
        }
        if (player.getCb().y > cb.y) {
            if (col.checkCollisionWithTile("down")) {
                y += speed;
            }
        }
        if (player.getCb().x < cb.x) {
            if (col.checkCollisionWithTile("left")) {
                x -= speed;
            }
        }
        if (player.getCb().x > cb.x) {
            if (col.checkCollisionWithTile("right")) {
                x += speed;
            }
        }

    }

    public void updateCollisionWithPlayer(){
        if(col.collisionWithEntity(player, cb.x, cb.y)||
        col.collisionWithEntity(player, cb.x + cb.width, cb.y)||
        col.collisionWithEntity(player, cb.x, cb.y + cb.height)||
        col.collisionWithEntity(player, cb.x + cb.width, cb.y + cb.height)){
            state.getGame().setStage(StateManager.postGameState);
        }
    }
    
    private void updateCollision() {
        cb.setBounds((x + texture.getWidth()/2) - 12 , y + texture.getHeight()/2, 25, 25);
    }
    
    public void updateSpeed(int amount){
        speed += amount;
    }
}
