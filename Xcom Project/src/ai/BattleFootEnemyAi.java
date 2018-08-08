package ai;

import actors.BattlePlayer;
import enemy.BattleEnemy;
import enemy.Enemy;
import java.awt.Point;
import java.io.Serializable;
import tile.PathTile;

public class BattleFootEnemyAi implements AI, Serializable {

    private BattleEnemy enemy;

    private int targetTile;
    private boolean detour;
    private Direction currentDirection;
    private int distanceToTile;
    private final int MOVECOST = -1;

    @Override
    public Point move() {
        //TODO: make sure enemy can have any movement speed
        for (PathTile tile : enemy.getPathTiles()) {
            if (tile.getNum() == targetTile) {
                if (enemy.getAp() <= 0) {
                    enemy.setEndTurn(true);
                    enemy.setAp(enemy.getMaxAp());
                    return new Point(0, 0);
                }
                enemy.setMoveing(true);
                if (tile.getHitbox().contains(enemy.getX(), enemy.getY())) {
                    targetTile = ++targetTile % enemy.getPathTiles().size();
                    return new Point(0, 0);
                }

                if (!detour) {
                    if (enemy.getX() > tile.getX() && !enemy.getCollision().isActorCollisionLeft()) {
                        currentDirection = Direction.LEFT;
                        enemy.changeAp(MOVECOST);
                        distanceToTile = Math.abs(enemy.getX() - tile.getX());
                        return getMovePoint(currentDirection);
                    }
                    if (enemy.getY() > tile.getY() && !enemy.getCollision().isActorCollisionUp()) {
                        currentDirection = Direction.UP;
                        enemy.changeAp(MOVECOST);
                        distanceToTile = Math.abs(enemy.getY() - tile.getX());
                        return getMovePoint(currentDirection);
                    }
                    if (enemy.getX() < tile.getX() && !enemy.getCollision().isActorCollisionRight()) {
                        currentDirection = Direction.RIGHT;
                        enemy.changeAp(MOVECOST);
                        distanceToTile = Math.abs(enemy.getX() - tile.getX());
                        return getMovePoint(currentDirection);
                    }
                    if (enemy.getY() < tile.getY() && !enemy.getCollision().isActorCollisionDown()) {
                        currentDirection = Direction.DOWN;
                        enemy.changeAp(MOVECOST);
                        distanceToTile = Math.abs(enemy.getY() - tile.getX());
                        return getMovePoint(currentDirection);
                    }
                    detour = true;
                } else {
                    //TODO: make sure detour always work
                    return detour();
                }
            }
        }
        enemy.setMoveing(false);
        return new Point(0, 0);
    }

    private Point detour() {
        if (currentDirection == Direction.RIGHT && enemy.getCollision().isActorCollisionRight()) {
            enemy.changeAp(MOVECOST);
            return getMovePoint(Direction.DOWN);
        }

        if (currentDirection == Direction.LEFT && enemy.getCollision().isActorCollisionLeft()) {
            enemy.changeAp(MOVECOST);
            return getMovePoint(Direction.DOWN);
        }

        if (currentDirection == Direction.DOWN && enemy.getCollision().isActorCollisionDown()) {
            enemy.changeAp(MOVECOST);
            return getMovePoint(Direction.LEFT);
        }

        if (currentDirection == Direction.UP && enemy.getCollision().isActorCollisionUp()) {
            enemy.changeAp(MOVECOST);
            return getMovePoint(Direction.LEFT);
        }

        detour = false;
        enemy.changeAp(MOVECOST);
        return getMovePoint(currentDirection);
    }

    private Point getMovePoint(Direction dir) {
        int distanceToTileDiff = enemy.getMovementSpeed();
        if (distanceToTile < enemy.getMovementSpeed()) {
            distanceToTileDiff = distanceToTile;
        }
        if (dir == Direction.RIGHT) {
            //Right
            int x = distanceToTileDiff;
            enemy.setDirection(3);
            return new Point(x, 0);
        }
        if (dir == Direction.LEFT) {
            //Left
            int x = -distanceToTileDiff;
            enemy.setDirection(2);
            return new Point(x, 0);
        }
        if (dir == Direction.UP) {
            //Up
            int y = -distanceToTileDiff;
            enemy.setDirection(1);
            return new Point(0, y);
        }
        if (dir == Direction.DOWN) {
            //Down
            int y = distanceToTileDiff;
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
        if (enemy.getAp() >= 45) {
            enemy.changeAp(-45);
            BattlePlayer player = (BattlePlayer) enemy.getWorld().getPlayer();
            player.changeHealth(-25);
            player.checkDeath();
        }
    }

    @Override
    public void setEnemy(Enemy enemy) {
        this.enemy = (BattleEnemy) enemy;
    }

}
