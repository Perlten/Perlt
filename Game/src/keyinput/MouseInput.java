package keyinput;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Perlt
 */
public class MouseInput implements MouseListener, MouseMotionListener {

    private boolean leftMouse;
    private int mouseX, mouseY;

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
        mouseX = me.getX();
        mouseY = me.getY();
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isLeftMouse() {
        return leftMouse;
    }
}
