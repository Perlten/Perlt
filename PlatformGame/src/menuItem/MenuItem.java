package menuItem;

import input.MouseInput;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import util.Util;

public class MenuItem {

    private BufferedImage image;
    private Rectangle hitBox;
    private MouseInput mouse;
    private String stateChange;
    private int x, y;

    public MenuItem(String path, String stateChange, MouseInput mouse, int x, int y) {
        this.mouse = mouse;
        this.x = x;
        this.y = y;
        this.stateChange = stateChange;
        image = Util.getImage(path);
        hitBox = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public String clicked() {
        
        if (hitBox.contains(mouse.getX(), mouse.getY())) {
            if (mouse.isLeftMouseClicked()) {
                return stateChange;
            }
        }
        return null;
    }

    public void render(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public String getStateChange() {
        return stateChange;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}
