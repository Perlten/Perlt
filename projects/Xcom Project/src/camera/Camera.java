package camera;

import actors.Actor;
import tile.Tile;

public class Camera {

    private int width, height;
    private Actor actor;
    public static int xOffset, yOffset;
    
    private final int focusSize = 32;
    

    public Camera(Actor focus, int gameWidth, int GameHeight) {
        this.actor = focus;
        this.width = gameWidth;
        this.height = GameHeight;
    }
    
    public void update(){
         xOffset = actor.getX() - width / 2 + focusSize / 2;
         yOffset = actor.getY() - height / 2 + focusSize / 2;

    }
    
    public static void resetCamera(){
        xOffset = 0;
        yOffset = 0;
    }

    public int getX() {
        return xOffset;
    }

    public int getY() {
        return yOffset;
    }
}
