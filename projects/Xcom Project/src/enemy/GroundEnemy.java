package enemy;

import ai.PathFollowAI;
import java.awt.Graphics;
import world.World;

public class GroundEnemy extends Enemy {

    public GroundEnemy(int x, int y, World world) {
        super(x, y, world, "groundEnemyTexture.png", 4, 3, new PathFollowAI(), 1);
    }

    @Override
    public void update() {
        updateHitbox();
        updateCollision();
        move();
        playerSeen();
    }

    @Override
    public void render(Graphics g) {
        animate(g);
    }

    @Override
    public void renderHighlight(Graphics g) {
        renderPathTile(g);
        g.drawPolygon(viewLine.getPolygon(direction));
    }

    @Override
    public void addHighlightedObject(int x, int y) {
        addPathTile(x, y);
    }

}
