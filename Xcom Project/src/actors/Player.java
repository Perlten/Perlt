package actors;

import display.FpsLock;
import input.KeyInput;
import java.awt.Graphics;
import java.awt.Rectangle;
import util.TextureUtil;
import world.World;

public class Player extends Actor {

    private KeyInput keyInput;
    
    //Animation
    private static final String TEXTUREPATH = "resources/texture/player/playerTexturePack.png";
    private static final int NUMOFANIMATIONS = 4;
    private static final int NUMOFFRAMES = 7;
    private int animation;
    private int frame;
    private FpsLock animationLock = new FpsLock(5);

    public Player(int x, int y, KeyInput keyInput, int movementSpeed, World world) {
        super(x, y, new Rectangle(32, 32), TextureUtil.getBufferedImagePack(TEXTUREPATH, NUMOFANIMATIONS, NUMOFFRAMES), movementSpeed, world);
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
        if (animationLock.check()) {
            frame = ++frame % NUMOFFRAMES;
        }
        g.drawImage(texture[animation][frame], x, y, null);
    }

    private void movement() {
        updateCollision();
        if (keyInput.isUp() && !collision.isPlayerCollisionUp()) {
            y -= movementSpeed;
            animation = 1;
        } else if (keyInput.isDown() && !collision.isPlayerCollisionDown()) {
            y += movementSpeed;
            animation = 0;
        } else if (keyInput.isLeft() && !collision.isPlayerCollisionLeft()) {
            x -= movementSpeed;
            animation = 2;
        } else if (keyInput.isRight() && !collision.isPlayerCollisionRight()) {
            x += movementSpeed;
            animation = 3;
        }
    }
}
