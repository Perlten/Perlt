package actors;

import java.awt.Point;
import java.io.Serializable;
import physics.Collision;
import tile.PathTile;

public class PathFollowAI implements AI, Serializable {

    private Enemy enemy;
    private int num;

    public PathFollowAI(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public Point move() {
        for (PathTile tile : enemy.getPathTiles()) {
            if (tile.getNum() == num) {

                if (tile.getHitbox().contains(enemy.getX(), enemy.getY())) {
                    num = ++num % enemy.getPathTiles().size();
                    return new Point(0, 0);
                }

                int x = 0;
                int y = 0;
                
                if (enemy.getX() > tile.getX() && !enemy.getCollision().isPlayerCollisionLeft()) {
                    //Left
                    x = -enemy.getMovementSpeed();
                    enemy.setDirection(2);
                } else if (enemy.getX() < tile.getX() && !enemy.getCollision().isPlayerCollisionRight()) {
                    //Right
                    x = enemy.getMovementSpeed();
                    enemy.setDirection(3);
                } else if (enemy.getY() > tile.getY() && !enemy.getCollision().isPlayerCollisionUp()) {
                    //Up
                    y = -enemy.getMovementSpeed();
                    enemy.setDirection(1);
                } else if (enemy.getY() < tile.getY() && !enemy.getCollision().isPlayerCollisionDown()) {
                    //Down
                    y = enemy.getMovementSpeed();
                    enemy.setDirection(0);
                }

                return new Point(x, y);
            }
        }
        return new Point(0, 0);
    }

    @Override
    public void reset() {
        num = 0;
    }

    @Override
    public void playerSeen() {
        System.out.println("Player seen!");
    }

}
