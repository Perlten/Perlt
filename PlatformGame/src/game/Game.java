/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import display.Display;
import display.FpsManager;
import handler.Handler;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import state.GameState;
import state.State;
import mapEditor.GameObjectManager;

/**
 *
 * @author Perlt
 */
public class Game implements Runnable {

    private int width, height;
    private String title;
    private Display display;
    private Graphics g;

    private FpsManager fps;
    private Thread thread;
    private boolean run;
    
    private KeyInput keyInput;
    private MouseInput mouseInput;
    
    private State currentState;
    

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        display = new Display(title, width, height);
        fps = new FpsManager(60);
        keyInput = new KeyInput();
        mouseInput = new MouseInput();
        currentState = new GameState(this);
        init();
    }

    private void init() {
        display.getFrame().addKeyListener(keyInput);
        display.getFrame().addMouseListener(mouseInput);
        display.getFrame().addMouseMotionListener(mouseInput);
        display.getCanvas().addMouseListener(mouseInput);
        display.getCanvas().addMouseMotionListener(mouseInput);
    }

    public void update() {
        keyInput.update();
        currentState.update();
    }

    public void render() {
        BufferStrategy bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(2);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear screen
        g.clearRect(0, 0, width, height);

        //Draw
        currentState.render(g);

        //End draw
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        while (run) {
            if (fps.check()) {
                update();
                render();
            }
            fps.printFps(g);
        }
    }

    public synchronized void start() {
        if (run) {
            return;
        }
        run = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!run) {
            return;
        }
        run = false;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
