package input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

    private Point mousePoint;
    private int x;
    private int y;

    private boolean leftMouse;

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON1) {
            leftMouse = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON1) {
            leftMouse = false;
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
        mousePoint = me.getPoint();
        x = me.getX();
        y = me.getY();
    }

    public Point getMousePoint() {
        return mousePoint;
    }

    public boolean isLeftMouse() {
        return leftMouse;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
}
