package actors;

import input.KeyInput;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import npc.Npc;
import world.World;

public class OverWorldPlayer extends Player {

    public OverWorldPlayer(int x, int y, KeyInput keyInput, World world) {
        super(x, y, keyInput, world, 3);
    }

    @Override
    public void update() {
        updateHitbox();
        move();
        interactWithNpc();
    }

    @Override
    public void render(Graphics g) {
        animate(g);
//        g.fillRect(x, y, hitbox.width, hitbox.height); //Draw hitbox
    }

    @Override
    public void updateFromLoad(World world) {
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

    private void move() {
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
        } else {
            moveing = false;
        }
    }

    private void interactWithNpc() {
        if (keyInput.isSpace()) {
            List<Point> pointList = collision.getPoints();
            for (Npc npc : world.getNpcList()) {
                for (Point point : pointList) {
                    if (npc.getViewLine().getPolygon(npc.getDirection()).contains(point)) {
                        npc.playerInteract();
                        return;
                    }
                }
            }
        }
    }
}
