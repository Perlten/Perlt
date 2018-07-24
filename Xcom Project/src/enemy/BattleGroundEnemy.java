package enemy;

import ai.BattleFootEnemyAi;
import java.awt.Graphics;
import world.World;

public class BattleGroundEnemy extends BattleEnemy {

    public BattleGroundEnemy(int x, int y, World world) {
        super(x, y, world, "groundEnemyTexture.png", 4, 3, new BattleFootEnemyAi(), 100, 1, 100);
    }

    @Override
    public void update() {
        if (endTurn) {
            endTurn = false;
        }
        updateHitbox();
        updateCollision();
        move();
        playerSeen();
    }

    @Override
    public void render(Graphics g) {
        animate(g);
        g.drawString("HP: " + health, x, y);
    }

    @Override
    public void renderHighlight(Graphics g) {
        renderPathTile(g);
        g.drawString("AP: " + ap, x, y + 45);

    }

    @Override
    public void addHighlightedObject(int x, int y) {
        addPathTile(x, y);
    }

    @Override
    public boolean isTurnOver() {
        return endTurn;
    }

    @Override
    public void onDeath() {
        if (health <= 0) {
            System.out.println("dead");
            world.removeEnemy(this);
        }
    }
}
