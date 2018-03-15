package game;

import display.Display;
import display.FpsLock;
import entity.Player;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import keyinput.KeyInput;

public class Game implements Runnable {

    private int width, height;
    private String title;

    private boolean running;
    private Thread thread;

    private Display display;
    private BufferStrategy bs;
    private Graphics g;

    private KeyInput keyInput;

    private FpsLock fpsLock;

    
    private Player player;

    public Game(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        init();
    }

    private void init() {
        this.display = new Display(width, height, title);
        this.keyInput = new KeyInput();
        display.getFrame().addKeyListener(keyInput);
        this.fpsLock = new FpsLock(60);
        this.player = new Player("resources/textures/ezio.png",100, 100, this);
    }

    public void update() {
        keyInput.update();
        player.update();
       
    }

    public void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        //Clear screen
        g.clearRect(0, 0, width, height);
        //Draw to screen

        player.render(g);
        
        //End draw
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        while (running) {
            if (fpsLock.check()) {
                update();
                render();
            }
        }
        stop();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public KeyInput getKeyInput() {
        return keyInput;
    }

}
