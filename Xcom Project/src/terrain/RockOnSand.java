package terrain;

import java.awt.Graphics;
import util.TextureUtil;

public class RockOnSand extends Terrain {

    public RockOnSand(int x, int y) {
        super(x, y, "rockOnSand.png");
    }

    @Override
    public void updateFromLoad() {
        texture = TextureUtil.getBufferedImage(texturePath);
    }

    @Override
    public void update() {
        updateHitbox();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture, x, y, null);
    }

    @Override
    public void renderHighlight(Graphics g) {
    }

    @Override
    public void addHighlightedObject(int x, int y) {
    }

}
