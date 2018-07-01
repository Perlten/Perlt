package tile;

import java.awt.Graphics;
import java.awt.Rectangle;
import util.TextureUtil;

public class PathTile extends Tile {

    private int num;

    public PathTile(int x, int y, int num) {
        super(x, y, new Rectangle(32, 32), "resources/texture/tile/test.png");
        this.num = num;
    }

    @Override
    public void updateFromLoad() {
        texture = TextureUtil.getBufferedImage(texturePath);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture, x, y, null);
        g.drawString(String.valueOf(num), x, y);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void renderHighlight(Graphics g) {
    }

    @Override
    public void addHighlightedObject(int x, int y) {
    }
    

}
