package ui;

import input.MouseInput;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import util.TextureUtil;

public abstract class Button implements UiElement{
    
    private int x, y;

    private BufferedImage texture;
    private String texturePath = "resources/texture/ui/";
    protected Rectangle hitbox;
    protected MouseInput mouse;
    
    public Button(int x, int y, String texturePathEnd, MouseInput mouse) {
        this.x = x;
        this.y = y;
        this.mouse = mouse;
        this.texturePath += texturePathEnd;
        texture = TextureUtil.getBufferedImage(texturePath);
        this.hitbox = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }
   
    @Override
    public void render(Graphics g) {
        g.drawImage(texture, x, y, null);
//        g.fillRect(x, x, hitbox.width, hitbox.height);
    }

}
