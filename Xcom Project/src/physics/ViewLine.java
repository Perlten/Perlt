package physics;

import actors.Actor;
import camera.Camera;
import java.awt.Point;
import java.awt.Polygon;
import java.util.List;
import tile.Tile;
import world.World;

public class ViewLine {

    private Actor actor;
    private World world;

    private final int STARTSIZE;
    private int size;

    private int leftSize;
    private int rightSize;

    private int[][] arrX = new int[4][3];
    private int[][] arrY = new int[4][3];

    public ViewLine(Actor actor, World world, int size) {
        this.actor = actor;
        this.world = world;
        this.size = size;
        this.STARTSIZE = size;
        this.leftSize = size;
        this.rightSize = size;
        updatePolygon();
    }

    public boolean canSeeActor(int side, Actor actor) {
        List<Point> pointList = actor.getCollision().getPoints();

        Polygon poly = getPolygon(side);

        for (Point point : pointList) {
            if (poly.contains(point)) {
                return true;
            }
        }
        return false;
    }

    private boolean canSeeSolidTile(int side) {
        Polygon poly = new Polygon(arrX[side], arrY[side], 3);
        for (Tile tile : world.getTileList()) {
            if (poly.contains(tile.getHitbox())) {
                //check if tile is to the right of actor
//                int debug1 = tile.getX();
//                int debug2 = actor.getX();
//                int debug3 = tile.getY();
//                int debug4 = actor.getY();

                if (side == 0 || side == 1) {
                    if (tile.getX() >= actor.getX()) {
                        rightSize = tile.getX() - actor.getX() - 32;
                    } else {
                        leftSize = actor.getX() - tile.getX() - 32;
                    }
                } else {
                    if (tile.getY() >= actor.getY()) {
                        leftSize = tile.getY() - actor.getY() - 32;
                    } else {
                        rightSize = actor.getY() - tile.getY() - 32;
                    }
                }
                return true;
            }
        }
        size = STARTSIZE;
        leftSize = STARTSIZE;
        rightSize = STARTSIZE;
        return false;
    }

    private void updatePolygon() {
        int x = actor.getX();
        int y = actor.getY();
        //Down
        arrX[0][0] = x + 16;
        arrY[0][0] = y + 16;

        arrX[0][1] = x - leftSize;
        arrY[0][1] = y + leftSize;

        arrX[0][2] = x + rightSize;
        arrY[0][2] = y + rightSize;

        //Up
        arrX[1][0] = x + 16;
        arrY[1][0] = y + 16;

        arrX[1][1] = x - leftSize;
        arrY[1][1] = y - leftSize;

        arrX[1][2] = x + rightSize;
        arrY[1][2] = y - rightSize;

        //Left
        arrX[2][0] = x + 16;
        arrY[2][0] = y + 16;

        arrX[2][1] = x - leftSize;
        arrY[2][1] = y - leftSize;

        arrX[2][2] = x - rightSize;
        arrY[2][2] = y + rightSize;

        //Right
        arrX[3][0] = x + 16;
        arrY[3][0] = y + 16;

        arrX[3][1] = x + rightSize;
        arrY[3][1] = y - rightSize;

        arrX[3][2] = x + leftSize;
        arrY[3][2] = y + leftSize;
    }

    /**
     * 0: Down 1: Up 2: Left 3: Right
     *
     * @param side
     * @return
     */
    public Polygon getPolygon(int side) {
        updatePolygon();
        while (canSeeSolidTile(side)) {
            updatePolygon();
        }
        return new Polygon(arrX[side], arrY[side], 3);
    }

}
