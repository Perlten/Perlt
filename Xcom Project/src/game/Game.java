/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import display.Display;
import display.FpsLock;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import state.GameState;
import state.MainMenuState;
import state.OptionState;
import state.State;
import state.StateType;

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
    private MouseInput mouseInput = new MouseInput();

    private FpsLock fpsLock = new FpsLock(60);

    private State currentState;

    public Game(String title, int width, int height) {
        this.display = new Display(width, height, title);
        init();
    }

    private void init() {
        currentState = new MainMenuState(mouseInput, keyInput);
        display.getFrame().addKeyListener(keyInput);
        display.getFrame().addMouseListener(mouseInput);
        display.getFrame().addMouseMotionListener(mouseInput);
        display.getCanvas().addMouseListener(mouseInput);
        display.getCanvas().addMouseMotionListener(mouseInput);
    }

    private void update() {
        keyInput.update();
        currentState.update();
        changeState();
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
        currentState.render(g);

        //Draw end
        bs.show();
        g.dispose();

    }
    
    private void changeState(){
        if(currentState.getStateType()== StateType.GAMESTATE){
            currentState = new GameState(keyInput, mouseInput);
        }
        if(currentState.getStateType()== StateType.MENUSTATE){
            currentState = new MainMenuState(mouseInput, keyInput);
        }if(currentState.getStateType() == StateType.OPTION){
            currentState = new OptionState();
        }
        
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
        Game game = new Game("Test", 1280, 720);
        game.start();
    }
}
