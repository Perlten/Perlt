package game;

import display.Display;
import display.FpsManager;
import gameNetwork.Network;
import input.KeyInput;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Scanner;
import player.Client;
import player.Host;

public class Game implements Runnable {

    private int width, height;
    private String title;
    private Display display;
    private Graphics g;

    private FpsManager fps;
    private Thread thread;
    private boolean run;

    private boolean hostGame;

    private Host host;
    private Client client;

    private KeyInput keyInput;

    //network
    private Network network;

    public Game(int width, int height, String title, boolean host) {
        this.hostGame = host;
        this.width = width;
        this.height = height;
        this.title = title;
        display = new Display(width, height, title);
        fps = new FpsManager(60);
        keyInput = new KeyInput();
        init();
    }

    private void init() {
        display.getFrame().addKeyListener(keyInput);
        host = new Host(keyInput);
        this.network = new Network(host, hostGame);
        client = new Client(network);

        Thread networkThread = new Thread(network);
        networkThread.start();
    }

    public void update() {
        keyInput.update();
        host.update();
        client.update();
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
        host.render(g);
        client.render(g);
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

    public static void main(String[] args) {
        System.out.println("Do you whanna host a game");
        boolean host = new Scanner(System.in).nextBoolean();
        System.out.println(host);
        Game game = new Game(300, 300, "Test", host);
        game.start();
    }

}
