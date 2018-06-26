package stage;

import actors.Player;
import input.KeyInput;
import java.awt.Graphics;

public class GameStage implements Stage {

    private Player player;

    public GameStage(KeyInput keyInput) {
        this.player = new Player(0, 0, "resources/texture/player/playerTexture.png", keyInput, 3);
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void render(Graphics g) {
        player.render(g);
    }
}
