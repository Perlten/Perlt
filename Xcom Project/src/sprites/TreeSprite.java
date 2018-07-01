package sprites;

import java.awt.Graphics;
import util.TextureUtil;

public class TreeSprite extends Sprite {

    public TreeSprite(int x, int y) {
        super(x, y, "tree.png");
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
