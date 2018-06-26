/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import display.Display;
import display.FpsLock;
import input.KeyInput;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import stage.GameStage;
import stage.Stage;

/**
 *
 * @author Perlt
 */
public class Game {

    private boolean run;

    private Display display;
    private BufferStrategy bs;
    private Graphics g;

    private KeyInput keyInput = new KeyInput();
    private FpsLock fpsLock = new FpsLock(60);

    private Stage currentStage;

    public Game(String title, int width, int height) {
        this.display = new Display(width, height, title);
        init();
    }

    private void init() {
        currentStage = new GameStage(keyInput);
        display.getFrame().addKeyListener(keyInput);
    }

    private void update() {
        keyInput.update();
        currentStage.update();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        g.clearRect(0, 0, display.getWidth(), display.getHeight());

        //Draw start
        currentStage.render(g);

        //Draw end
        bs.show();
        g.dispose();

    }

    public void start() {
        if (!run) {
            run = true;
            run();
        } else {
            System.out.println("Allready running");
        }
    }

    private void run() {
        while (run) {
            if (fpsLock.check()) {
                update();
                render();
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game("Test", 800, 600);
        game.start();
    }
}
