package tile;

import java.awt.Graphics;
import java.awt.Rectangle;
import util.TextureUtil;

public class RockTile extends Tile {

    public RockTile(int x, int y) {
        super(x, y, new Rectangle(32, 32), "resources/texture/tile/rockTile.png");
    }

    @Override
    public void update() {
        updateHitbox();
    }

    @Override
    public void render(Graphics g) {
//        g.fillRect(x, y, hitbox.width, hitbox.height); //draw hitbox
        g.drawImage(texture, x, y, null);
    }

    @Override
    public void updateFromLoad() {
           texture = TextureUtil.getBufferedImage(texturePath);
    }

    @Override
    public void renderHighlight(Graphics g) {
    }

    @Override
    public void addHighlightedObject(int x, int y) {
    }

}
