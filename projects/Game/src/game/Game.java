package game;

import display.Display;
import display.FpsLock;
import entity.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import keyinput.KeyInput;
import keyinput.MouseInput;
import state.GameState;
import state.PostGameState;
import state.State;
import state.StateManager;
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
    private MouseInput mouseInput;

    private FpsLock fpsLock;

    private State CurrentState;

    public Game(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        display = new Display(width, height, title);
        keyInput = new KeyInput();
        mouseInput = new MouseInput();
        fpsLock = new FpsLock(60);
        
        init();
    }

    private void init() {
        display.getFrame().addKeyListener(keyInput);
        display.getFrame().addMouseListener(mouseInput);
        display.getFrame().addMouseMotionListener(mouseInput);
        display.getCanvas().addMouseListener(mouseInput);
        display.getCanvas().addMouseMotionListener(mouseInput);
        
        TileManager.init();
        StateManager.initStates(this);
        CurrentState = StateManager.preGameState;
    }

    public void update() {
        keyInput.update();
        CurrentState.update();

        if (keyInput.isTest()) {
            CurrentState = StateManager.postGameState;
        }

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

        CurrentState.render(g);
        debugMode();

        //End draw
        bs.show();
        g.dispose();
    }
    
    public void setStage(State state){
        CurrentState = state;
    }

    private void debugMode() {
        if (keyInput.isDebug() && CurrentState.equals(StateManager.gameState)) {
            int playerX = StateManager.gameState.getPlayer().getCb().x;
            int playerY = StateManager.gameState.getPlayer().getCb().y;
            g.setColor(Color.red);
            g.drawString("Pos X: " + String.valueOf(playerX), 480, 20);
            g.drawString("Pos Y: " + String.valueOf(playerY), 480, 35);
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

    public MouseInput getMouseInput() {
        return mouseInput;
    }

    public Display getDisplay() {
        return display;
    }
    
    
}
