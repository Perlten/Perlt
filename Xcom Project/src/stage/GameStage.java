package stage;

import actors.Player;
import input.KeyInput;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import world.World;
import world.World0;

public class GameStage implements Stage {

    private Player player;
    private List<World> worldList = new ArrayList<>();
    private World currentWorld;
    
    
    
    public GameStage(KeyInput keyInput) {
        worldList.add(new World0());
        this.currentWorld = worldList.get(0);
        this.player = new Player(0, 0, keyInput, 3, currentWorld);
    }

    @Override
    public void update() {
        currentWorld.update();
        
        player.update();
    }

    @Override
    public void render(Graphics g) {
        currentWorld.render(g);
        player.render(g);
    }
}
