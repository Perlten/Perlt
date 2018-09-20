package ui;

import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class TextInput implements UiElement {

    private String text = "";
    private KeyInput keyInput;
    private MouseInput mouseInput;
    
    private Rectangle hitbox;
    
    private boolean texting = false;

    public TextInput(int x, int y,int size, KeyInput keyInput, MouseInput mouseInput) {
        this.hitbox = new Rectangle(x, y, size, size / 4);
        this.mouseInput = mouseInput;
        this.keyInput = keyInput;
    }
    
    @Override
    public void init() {
    }
    
    @Override
    public void onInteract() {
        if(hitbox.contains(mouseInput.getX(), mouseInput.getY()) && mouseInput.isLeftMouse()){
            texting = true;
        }
        if(texting){
            boolean[] keys = keyInput.getKeysReleased();
            for (int i = 0; i < keys.length; i++) {
                if(keys[i]){
                    if(keys[KeyEvent.VK_SPACE]){
                        text += " ";
                        keys[i] = false;
                        return;
                    }
                    if(keys[KeyEvent.VK_BACK_SPACE]){
                        text = text.substring(0, text.length() - 1);
                        keys[i] = false;
                        return;
                    }
                    if(keys[KeyEvent.VK_ENTER]){
                        texting = false;
                        keys[i] = false;
                        return;
                    }
                    text += KeyEvent.getKeyText(i);
                    keys[i] = false;
                }
            }
        }
        
    }

    @Override
    public void render(Graphics g) {
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        g.drawString(text,hitbox.x, hitbox.y + 25);
    }

    public String getText() {
        return text;
    }
}
