/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Perlt
 */
public class MouseInput implements MouseListener, MouseMotionListener{
    
    private boolean leftMouseClicked, rightMouseClicked;
    private int x, y;
    
    public MouseInput() {
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(me.getButton() == MouseEvent.BUTTON1){
            leftMouseClicked = true;
        }
        if(me.getButton() == MouseEvent.BUTTON3){
            rightMouseClicked = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(me.getButton() == MouseEvent.BUTTON1){
            leftMouseClicked = false;
        }
        if(me.getButton() == MouseEvent.BUTTON3){
            rightMouseClicked = false;
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
        x = me.getX();
        y = me.getY();
    }

    public void setMouse1False(){
        leftMouseClicked = false;
    }
    
    public boolean isLeftMouseClicked() {
        return leftMouseClicked;
    }

    public boolean isRightMouseClicked() {
        return rightMouseClicked;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
