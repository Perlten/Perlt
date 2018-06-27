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

    private Player player;
    private List<World> worldList = new ArrayList<>();
    private World currentWorld;
    
    private Camera camera;
    
    
    public GameStage(KeyInput keyInput, MouseInput mouseInput, int gameWidth, int gameHeight) {
        this.player = new Player(0, 0, keyInput, 3, currentWorld);
        this.camera = new Camera(player, gameWidth, gameHeight);
        worldList.add(new World0(player, mouseInput));
        this.currentWorld = worldList.get(0);
        player.setWorld(currentWorld);
    }

    @Override
    public void update() {
        camera.update();
        currentWorld.update();
        player.update();
    }

    @Override
    public void render(Graphics g) {
        g.translate(-Camera.xOffset, -Camera.yOffset);
        currentWorld.render(g);
        player.render(g);
        g.translate(Camera.xOffset, Camera.yOffset);
        //Draw fixed graphics here
        g.drawRect(0, 0, 32, 32);
    }
}
