package ui;

import input.MouseInput;

public class ExitButton extends Button{

    public ExitButton(int x, int y, String texturePathEnd, MouseInput mouse) {
        super(x, y, texturePathEnd, mouse);
    }

    @Override
    public void init() {
    }

    @Override
    public void onInteract() {
        if(hitBoxContains()){
            System.exit(0);
        }
    }

}
