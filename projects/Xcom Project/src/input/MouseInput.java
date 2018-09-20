package input;

import camera.Camera;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

    private Point mousePoint;
    private int x;
    private int y;

    private boolean leftMouse, rightMouse;

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON1) {
            leftMouse = true;
        }
        if (me.getButton() == MouseEvent.BUTTON3) {
            rightMouse = true;
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        mousePoint = new Point(me.getX() + Camera.xOffset, me.getY() + Camera.yOffset);
        x = me.getX();
        y = me.getY();
    }

    public Point getMousePoint() {
        return mousePoint;
    }

    public boolean isLeftMouse() {
        boolean temp = leftMouse;
        leftMouse = false;
        return temp;
    }

    public boolean isRightMouse() {
        boolean temp = rightMouse;
        rightMouse = false;
        return temp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
