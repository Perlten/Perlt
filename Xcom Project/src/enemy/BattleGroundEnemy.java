package enemy;

import ai.BattleFootEnemyAi;
import java.awt.Graphics;
import world.World;

public class BattleGroundEnemy extends Enemy {

    public BattleGroundEnemy(int x, int y, World world) {
        super(x, y, world, "groundEnemyTexture.png", 4, 3, new BattleFootEnemyAi());
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
    }

    @Override
    public void addHighlightedObject(int x, int y) {
        addPathTile(x, y);
    }

}
