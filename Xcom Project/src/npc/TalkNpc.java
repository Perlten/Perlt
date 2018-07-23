package npc;

import java.awt.Graphics;
import ui.NpcTextBox;
import world.World;

public class TalkNpc extends Npc {
    
    private NpcTextBox textBox = new NpcTextBox(this);

    public TalkNpc(int x, int y, World world) {
        super(x, y, "playerTexturePack.png", world, 4, 7);
    }


    @Override
    public void update() {
        updateHitbox();
        updateCollision();
    }

    @Override
    public void render(Graphics g) {
        animate(g);
    }

    @Override
    public void renderTextBox(Graphics g) {
        textBox.render(g);
    }
    
    @Override
    public void playerInteract() {
        textBox.onInteract();
    }
    
    @Override
    public void renderHighlight(Graphics g) {
        g.drawPolygon(viewLine.getPolygon(direction));
    }

    @Override
    public void addHighlightedObject(int x, int y) {
        direction = ++direction % 4;
    }


}
