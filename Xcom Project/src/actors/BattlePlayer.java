package actors;

import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.awt.Point;
import util.TextureUtil;
import world.World;

public class BattlePlayer extends Player implements BattleObject{

    private MouseInput mouse;
    private boolean clicked;

    private Point goingTo;
    private int goingX;
    private int goingY;

    private int ap;
    private boolean playerTurn = true;

    public BattlePlayer(int x, int y, KeyInput keyInput, MouseInput mouse, World world) {
        super(x, y, keyInput, world, 3);
        this.mouse = mouse;

        this.ap = 100;
    }

    @Override
    public void updateFromLoad(World world) {
    }

    @Override
    public void update() {
        if(!playerTurn){
            playerTurn = true;
            ap = 100;
        }
        endTurn();
        updateHitbox();
        move();
    }
    
    @Override
    public void render(Graphics g) {
        animate(g);
        renderGoingToPoint(g);
        //TODO: make pretty
        g.drawString(String.valueOf(ap), x, y - 25);
    }

    private void endTurn(){
        if(keyInput.isSpace()){
            playerTurn = false;
            return;
        }
    }
    
    private void renderGoingToPoint(Graphics g) {
        if (goingTo != null) {
            int renderX = goingTo.x - 8;
            int renderY = goingTo.y - 8;
            g.drawImage(TextureUtil.getBufferedImage("resources/texture/player/x.png"), renderX, renderY, null);
        }
    }

    private void move() {
        updateCollision();
        if (mouse.isLeftMouse()) {
            if (ap >= 10) {
                goingTo = mouse.getMousePoint();
                //finds to ap cost for the "trip" and checks if player has enough ap
                int distance = (Math.abs(x - goingTo.x) + (Math.abs(y - goingTo.y)));
                int apCost = distance / 10;
                if (ap >= apCost) {
                    moveing = true;
                    clicked = true;
                    ap -= apCost;
                } else {
                    goingTo = null;
                }

            }
        } else if (clicked) {
            goingX = goingTo.x - hitbox.width / 2;
            goingY = goingTo.y - hitbox.height / 2;

            int remiander = (Math.abs(x - goingX)) % movementSpeed;
            goingX -= remiander;
            remiander = (Math.abs(y - goingY)) % movementSpeed;
            goingY -= remiander;

            if (goingX != x || goingY != y) {
                //Left
                if (x > goingX && !collision.isActorCollisionLeft()) {
                    x -= movementSpeed;
                    direction = 2;
                    return;
                }
                //Right
                if (x < goingX && !collision.isActorCollisionRight()) {
                    x += movementSpeed;
                    direction = 3;
                    return;
                }
                //Up
                if (y > goingY && !collision.isActorCollisionUp()) {
                    y -= movementSpeed;
                    direction = 1;
                    return;
                }
                //Down
                if (y < goingY && !collision.isActorCollisionDown()) {
                    y += movementSpeed;
                    direction = 0;
                    return;
                }
            } else {
                goingTo = null;
                moveing = false;
                clicked = false;
            }
        }
    }

    @Override
    public void renderHighlight(Graphics g) {
    }

    @Override
    public void addGameObject(World world, int x, int y) {
    }

    @Override
    public void addHighlightedObject(int x, int y) {
    }

    @Override
    public boolean isTurnOver() {
        return playerTurn;
    }

}
