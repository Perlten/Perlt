package ui;

import camera.Camera;
import display.Display;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import npc.Npc;

public class NpcTextBox implements UiElement, Serializable {

    private String text;
    private boolean render;
    private Npc npc;

    public NpcTextBox(Npc npc) {
        this.text = TextOptionManager.getText();
        this.npc = npc;
    }

    @Override
    public void onInteract() {
        render = !render;
    }

    @Override
    public void render(Graphics g) {
        if (render && npc.getViewLine().canSeeActor(npc.getDirection(), npc.getWorld().getPlayer())) {
            g.translate(Camera.xOffset, Camera.yOffset);

            int width = Display.width / 2;
            int recSizeX = width;
            int recSizeY = 75;
            int startX = width - (width / 2);
            int startY = Display.height - (Display.height / 3);

            Font defaultFont = g.getFont();

            g.fillRect(startX, startY, recSizeX, recSizeY);
            g.setColor(Color.WHITE);
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
            g.drawString(text, startX + 20, startY + 20);

            g.setFont(defaultFont);
            g.setColor(Color.BLACK);

            g.translate(-Camera.xOffset, -Camera.yOffset);
        } else {
            render = false;
        }
    }

    @Override
    public void init() {

    }

    private static class TextOptionManager {
        private static final List<String> textList = new ArrayList<>();

        static {
            textList.add("Fuck off");
            textList.add("What are you looking at");
            textList.add("Hey sexy");
        }
        
        public static String getText(){
            Random ra = new Random();
            int index = ra.nextInt(textList.size());
            return textList.get(index);
        }
    }
}
