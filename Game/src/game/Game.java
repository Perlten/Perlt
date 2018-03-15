package game;

import display.Display;
import display.FpsLock;
import entity.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import keyinput.KeyInput;
import state.GameState;
import tile.TileManager;

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

    private GameState gameState;

    public Game(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        init();
    }

    private void init() {
        display = new Display(width, height, title);
        keyInput = new KeyInput();
        display.getFrame().addKeyListener(keyInput);
        fpsLock = new FpsLock(60);
        TileManager.init();
        gameState = new GameState(this);
    }

    public void update() {
        keyInput.update();
        gameState.update();
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

        gameState.render(g);
        debugMode();
        
        //End draw
        bs.show();
        g.dispose();
    }
    
    private void debugMode(){
        if(keyInput.isDebug()){
            int playerX = gameState.getPlayer().getX();
            int playerY = gameState.getPlayer().getY();
            g.setColor(Color.red);
            g.drawString("Pos X: " + String.valueOf(playerX), 10, 25);
            g.drawString("Pos Y: " + String.valueOf(playerY), 10, 40);
        }
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
