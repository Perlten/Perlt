package stage;

import actors.Player;
import input.KeyInput;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import physics.Collision;
import world.World;
import world.World0;

public class GameStage implements Stage {

    private Player player;
    private List<World> worldList = new ArrayList<>();
    private World currentWorld;
    
    private Collision collision;
    
    
    public GameStage(KeyInput keyInput) {
        worldList.add(new World0());
        this.currentWorld = worldList.get(0);
        this.player = new Player(0, 0, "resources/texture/player/playerTexture.png", keyInput, 3, null);
        this.collision = new Collision(player, currentWorld);
        player.setCollision(collision);
    }

    @Override
    public void update() {
        collision.update();
        currentWorld.update();
        
        player.update();
    }

    @Override
    public void render(Graphics g) {
        currentWorld.render(g);
        player.render(g);
    }
}
