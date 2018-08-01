package physics;

import actors.Actor;
import camera.Camera;
import java.awt.Point;
import java.awt.Polygon;
import java.util.List;
import world.World;

public class ViewLine {

    private Actor actor;
    private World world;

    private final int SIZE;
    private int[][] arrX = new int[4][3];
    private int[][] arrY = new int[4][3];

    public ViewLine(Actor actor, World world, int size) {
        this.actor = actor;
        this.world = world;
        this.SIZE = size;
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

    private void updatePolygon() {
        int x = actor.getX();
        int y = actor.getY();
        //Down
        arrX[0][0] = x + 16;
        arrY[0][0] = y + 16;

        arrX[0][1] = x - SIZE;
        arrY[0][1] = y + SIZE;

        arrX[0][2] = x + SIZE;
        arrY[0][2] = y + SIZE;

        //Up
        arrX[1][0] = x + 16;
        arrY[1][0] = y + 16;

        arrX[1][1] = x - SIZE;
        arrY[1][1] = y - SIZE;

        arrX[1][2] = x + SIZE;
        arrY[1][2] = y - SIZE;

        //Left
        arrX[2][0] = x + 16;
        arrY[2][0] = y + 16;

        arrX[2][1] = x - SIZE;
        arrY[2][1] = y - SIZE;

        arrX[2][2] = x - SIZE;
        arrY[2][2] = y + SIZE;

        //Right
        arrX[3][0] = x + 16;
        arrY[3][0] = y + 16;

        arrX[3][1] = x + SIZE;
        arrY[3][1] = y - SIZE;

        arrX[3][2] = x + SIZE;
        arrY[3][2] = y + SIZE;
    }

    /**
     * 0: Down 1: Up 2: Left 3: Right
     *
     * @param side
     * @return
     */
    public Polygon getPolygon(int side) {
        updatePolygon();
        return new Polygon(arrX[side], arrY[side], 3);
    }

}
