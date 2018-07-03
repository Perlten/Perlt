package stage;

import actors.Player;
import camera.Camera;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import world.World;
import world.World0;

public class GameStage implements Stage {

    private List<World> worldList = new ArrayList<>();
    private World currentWorld;
    
    
    
    public GameStage(KeyInput keyInput, MouseInput mouseInput, int gameWidth, int gameHeight) {
       
        worldList.add(new World0(mouseInput, keyInput));
        this.currentWorld = worldList.get(0);
    }

    @Override
    public void update() {
        currentWorld.update();
    }

    @Override
    public void render(Graphics g) {
        g.translate(-Camera.xOffset, -Camera.yOffset);
        currentWorld.render(g);
        g.translate(Camera.xOffset, Camera.yOffset);
        //Draw fixed graphics here
        g.drawRect(0, 0, 32, 32);
    }
}
