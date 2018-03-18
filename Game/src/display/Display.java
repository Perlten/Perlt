package display;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Display {
    
    private JFrame frame;
    private Canvas canvas;
    private int width, height;
    private String title;

    public Display(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        makeFrame();
    }
    
    private void makeFrame(){
        frame = new JFrame(title);
        
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

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    
}
