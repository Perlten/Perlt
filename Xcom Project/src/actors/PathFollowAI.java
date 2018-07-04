package actors;

import java.awt.Point;
import java.io.Serializable;
import physics.Collision;
import tile.PathTile;

public class PathFollowAI implements AI, Serializable {

    private Enemy enemy;
    private int num;

    private boolean detour;

    private Direction currentDirection;

    public PathFollowAI(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public Point move() {
        for (PathTile tile : enemy.getPathTiles()) {
            if (tile.getNum() == num) {
                enemy.setMoveing(true);
                if (tile.getHitbox().contains(enemy.getX(), enemy.getY())) {
                    num = ++num % enemy.getPathTiles().size();
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
    
    private Point detour(){
        if(currentDirection == Direction.RIGHT && enemy.getCollision().isActorCollisionRight()){
            return getMovePoint(Direction.DOWN);
        }
        
        if(currentDirection == Direction.LEFT && enemy.getCollision().isActorCollisionLeft()){
            return getMovePoint(Direction.DOWN);
        }
        
        if(currentDirection == Direction.DOWN && enemy.getCollision().isActorCollisionDown()){
            return getMovePoint(Direction.LEFT);
        }
        
        if(currentDirection == Direction.UP && enemy.getCollision().isActorCollisionUp()){
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
            dir = Direction.RIGHT;
            return new Point(x, 0);

        }
        if (dir == Direction.LEFT) {
            //Left
            int x = -enemy.getMovementSpeed();
            enemy.setDirection(2);
            dir = Direction.LEFT;
            return new Point(x, 0);
        }
        if (dir == Direction.UP) {
            //Up
            int y = -enemy.getMovementSpeed();
            enemy.setDirection(1);
            dir = Direction.UP;
            return new Point(0, y);
        }
        if (dir == Direction.DOWN) {
            //Down
            int y = enemy.getMovementSpeed();
            dir = Direction.DOWN;
            enemy.setDirection(0);
            return new Point(0, y);
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
