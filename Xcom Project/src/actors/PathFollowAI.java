package actors;

import java.awt.Point;
import java.io.Serializable;
import physics.Collision;
import tile.PathTile;

public class PathFollowAI implements AI, Serializable {

    private Enemy enemy;
    private int num;

    private boolean detour;

    private Direction dir;

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

                if (!detour) {
                    if (enemy.getX() > tile.getX() && !enemy.getCollision().isActorCollisionLeft()) {
                        //Left
                        x = -enemy.getMovementSpeed();
                        enemy.setDirection(2);
                        dir = Direction.LEFT;
                        return new Point(x, y);
                    }
                    if (enemy.getY() > tile.getY() && !enemy.getCollision().isActorCollisionUp()) {
                        //Up
                        y = -enemy.getMovementSpeed();
                        enemy.setDirection(1);
                        dir = Direction.UP;
                        return new Point(x, y);
                    }
                    if (enemy.getX() < tile.getX() && !enemy.getCollision().isActorCollisionRight()) {
                        //Right
                        x = enemy.getMovementSpeed();
                        enemy.setDirection(3);
                        dir = Direction.RIGHT;
                        return new Point(x, y);
                    }
                    if (enemy.getY() < tile.getY() && !enemy.getCollision().isActorCollisionDown()) {
                        //Down
                        y = enemy.getMovementSpeed();
                        dir = Direction.DOWN;
                        enemy.setDirection(0);
                        return new Point(x, y);
                    }
                    detour = true;
                } else {
                    return detour();
                }
            }
        }
        return new Point(0, 0);
    }

    private Point detour() {
        if (dir == Direction.RIGHT) {
            if (!enemy.getCollision().isActorCollisionRight()) {
                detour = false;
                enemy.setDirection(3);
                return new Point(enemy.getMovementSpeed(), 0);
            }
            if (!enemy.getCollision().isActorCollisionDown()) {
                enemy.setDirection(0);
                return new Point(0, enemy.getMovementSpeed());
            }
        }
        if (dir == Direction.LEFT) {
            if (!enemy.getCollision().isActorCollisionLeft()) {
                detour = false;
                enemy.setDirection(2);
                return new Point(-enemy.getMovementSpeed(), 0);
            }
            if (!enemy.getCollision().isActorCollisionDown()) {
                enemy.setDirection(0);
                return new Point(0, enemy.getMovementSpeed());
            }
        }
        if (dir == Direction.UP) {
            if (!enemy.getCollision().isActorCollisionUp()) {
                detour = false;
                enemy.setDirection(1);
                return new Point(0, -enemy.getMovementSpeed());
            }
            if (!enemy.getCollision().isActorCollisionLeft()) {
                enemy.setDirection(2);
                return new Point(-enemy.getMovementSpeed(), 0);
            }
        }
        if (dir == Direction.DOWN) {
            if (!enemy.getCollision().isActorCollisionDown()) {
                detour = false;
                enemy.setDirection(0);
                return new Point(0, enemy.getMovementSpeed());
            }
            if (!enemy.getCollision().isActorCollisionLeft()) {
                enemy.setDirection(2);
                return new Point(-enemy.getMovementSpeed(), 0);
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
    }

}

enum Direction {
    LEFT, RIGHT, UP, DOWN;
}
