package actors;

import display.FpsLock;
import input.KeyInput;
import java.awt.Graphics;
import java.awt.Rectangle;
import physics.ViewLine;
import util.TextureUtil;
import world.World;

public class Player extends Actor {

    private KeyInput keyInput;

    //Animation
    private int direction;
    private int frame;
    private FpsLock animationLock = new FpsLock(10);
    
    private boolean moveing;

    public Player(int x, int y, KeyInput keyInput, int movementSpeed, World world) {
        super(x, y, new Rectangle(32, 32), "playerTexturePack.png", movementSpeed, world, 4, 7);
        this.keyInput = keyInput;
    }

    @Override
    public void update() {
        updateHitbox();
        movement();
    }

    @Override
    public void render(Graphics g) {
        animate(g);
//        g.fillRect(x, y, hitbox.width, hitbox.height); //Draw hitbox
    }

    private void animate(Graphics g) {
        if (animationLock.check() && moveing) {
            frame++;
        }
        g.drawImage(texture[direction][frame % numOfFrames], x, y, null);
    }

    private void movement() {
        updateCollision();
        moveing = true;
        if (keyInput.isUp() && !collision.isActorCollisionUp()) {
            y -= movementSpeed;
            direction = 1;
        } else if (keyInput.isDown() && !collision.isActorCollisionDown()) {
            y += movementSpeed;
            direction = 0;
        } else if (keyInput.isLeft() && !collision.isActorCollisionLeft()) {
            x -= movementSpeed;
            direction = 2;
        } else if (keyInput.isRight() && !collision.isActorCollisionRight()) {
            x += movementSpeed;
            direction = 3;
        }else{
            moveing = false;
        }
    }

    @Override
    public void updateFromLoad(World world) {
        
    }

    @Override
    public void renderHighlight(Graphics g) {
    }

    @Override
    public void addHighlightedObject(int x, int y) {
    }

    @Override
    public void addGameObject(World world, int x, int y) {
    }
}
