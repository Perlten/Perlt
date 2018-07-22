package ai;

import enemy.Enemy;
import java.awt.Point;
import java.io.Serializable;
import tile.PathTile;

public class BattleFootEnemyAi implements AI, Serializable {

    private Enemy enemy;
    
    private int targetTile;
    private boolean detour;
    private Direction currentDirection;

    @Override
    public Point move() {
        for (PathTile tile : enemy.getPathTiles()) {
            if (tile.getNum() == targetTile) {
                enemy.setMoveing(true);
                if (tile.getHitbox().contains(enemy.getX(), enemy.getY())) {
                    targetTile = ++targetTile % enemy.getPathTiles().size();
                    return new Point(0, 0);
                }

                if (!detour) {
                    if (enemy.getX() > tile.getX() && !enemy.getCollision().isActorCollisionLeft()) {
                        currentDirection = Direction.LEFT;
                        return getMovePoint(currentDirection);
                    }
                    if (enemy.getY() > tile.getY() && !enemy.getCollision().isActorCollisionUp()) {
                        currentDirection = Direction.UP;
                        return getMovePoint(currentDirection);
                    }
                    if (enemy.getX() < tile.getX() && !enemy.getCollision().isActorCollisionRight()) {
                        currentDirection = Direction.RIGHT;
                        return getMovePoint(currentDirection);
                    }
                    if (enemy.getY() < tile.getY() && !enemy.getCollision().isActorCollisionDown()) {
                        currentDirection = Direction.DOWN;
                        return getMovePoint(currentDirection);
                    }
                    detour = true;
                } else {
                    //TODO:
                    return detour();
                }
            }
        }
        enemy.setMoveing(false);
        return new Point(0, 0);
    }

    private Point detour() {
        if (currentDirection == Direction.RIGHT && enemy.getCollision().isActorCollisionRight()) {
            return getMovePoint(Direction.DOWN);
        }

        if (currentDirection == Direction.LEFT && enemy.getCollision().isActorCollisionLeft()) {
            return getMovePoint(Direction.DOWN);
        }

        if (currentDirection == Direction.DOWN && enemy.getCollision().isActorCollisionDown()) {
            return getMovePoint(Direction.LEFT);
        }

        if (currentDirection == Direction.UP && enemy.getCollision().isActorCollisionUp()) {
            return getMovePoint(Direction.LEFT);
        }

        detour = false;
        return getMovePoint(currentDirection);
    }

    private Point getMovePoint(Direction dir) {
        if (dir == Direction.RIGHT) {
            //Right
            int x = enemy.getMovementSpeed();
            enemy.setDirection(3);
            return new Point(x, 0);

        }
        if (dir == Direction.LEFT) {
            //Left
            int x = -enemy.getMovementSpeed();
            enemy.setDirection(2);
            return new Point(x, 0);
        }
        if (dir == Direction.UP) {
            //Up
            int y = -enemy.getMovementSpeed();
            enemy.setDirection(1);
            return new Point(0, y);
        }
        if (dir == Direction.DOWN) {
            //Down
            int y = enemy.getMovementSpeed();
            enemy.setDirection(0);
            return new Point(0, y);
        }
        return new Point(0, 0);
    }

    @Override
    public void reset() {
         targetTile = 0;
    }

    @Override
    public void playerSeen() {
        System.out.println("Seen");
    }

    @Override
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

}
