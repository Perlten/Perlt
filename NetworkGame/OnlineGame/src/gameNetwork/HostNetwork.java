package gameNetwork;

import display.FpsManager;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import onlinegamecommen.NetworkUtil;
import onlinegamecommen.PlayerPacket;
import player.Host;

public class HostNetwork implements Runnable {

    private Socket socket;
    private String name;
    private Scanner scanner = new Scanner(System.in);
    private Host player;
    private PlayerPacket receivedPlayerPacket;

    FpsManager fps = new FpsManager(60);

    public HostNetwork(Host player) {
        this.player = player;
    }

    public void networking() throws Exception {
        connecToServer();

        //Send and read data
        while (true) {
            if (fps.check()) {
                PlayerPacket sendPlayerPacket = new PlayerPacket(player.getX(), player.getY());
                NetworkUtil.sendPlayerPacket(socket, sendPlayerPacket);

                receivedPlayerPacket = NetworkUtil.readPlayerPacket(socket);
                fps.printFps();
            }
        }
    }

    private void connecToServer() throws IOException {
        socket = new Socket("159.89.99.250", 5000);
        System.out.println("Enter name");
        name = scanner.nextLine();
        NetworkUtil.writeString(socket, name);
        System.out.println(NetworkUtil.readString(socket));
    }

    @Override
    public void run() {
        try {
            networking();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }

    public PlayerPacket getReceivedPlayerPacket() {
        return receivedPlayerPacket;
    }
}
