package ui;

import java.awt.Graphics;

public interface UiElement {

    public void init();
    public void onInteract();
    public void render(Graphics g);
    
}
