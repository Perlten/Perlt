/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuItem;

import input.MouseInput;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import util.Util;

/**
 *
 * @author Perlt
 */
public class Button {

    private BufferedImage image;
    private Rectangle hitbox;
    private int x, y;
    private MouseInput mouse;
    private boolean render = true, clickAble = true;

    public Button(String path, int x, int y, MouseInput mouse) {
        this.x = x;
        this.y = y;
        this.mouse = mouse;
        image = Util.getImage(path);
        hitbox = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public void update() {

    }

    public void render(Graphics g) {
        if (render) {
            g.drawImage(image, x, y, null);
        }
    }

    public boolean isClicked() {
        if (clickAble) {
            return hitbox.contains(mouse.getX(), mouse.getY());
        }
        return false;
    }

    public void setClickAble(boolean clickAble) {
        this.clickAble = clickAble;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

}
