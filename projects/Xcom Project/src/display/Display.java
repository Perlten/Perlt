package display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display {

    private JFrame frame;
    private Canvas canvas;

    public static int width, height;

    public Display(int width, int height, String title) {
        Display.width = width;
        Display.height = height;
        frame = new JFrame(title);
        
        createDisplay();
    }

    private void createDisplay() {
        frame.setSize(width, height);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    
}
