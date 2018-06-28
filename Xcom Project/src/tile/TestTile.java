package tile;

import java.awt.Graphics;

public class TestTile extends Tile {

     public TestTile(int x, int y) {
        super(x, y, null, "resources/texture/tile/test.png");
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

}
