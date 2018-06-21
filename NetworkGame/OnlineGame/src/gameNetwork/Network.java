package gameNetwork;

import display.FpsManager;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import onlinegamecommen.NetworkUtil;
import onlinegamecommen.PlayerPacket;
import player.Host;

public class Network implements Runnable {

    private Socket socket;
    private String name;
    private Scanner scanner = new Scanner(System.in);
    private Host player;
    private PlayerPacket receivedPlayerPacket;
    private boolean isHost;

    FpsManager fps = new FpsManager(60);

    public Network(Host player, boolean isHost) {
        this.player = player;
        this.isHost = isHost;
    }

    public void networking() throws Exception {
        connecToServer();

        //Send and read data
        while (true) {
            if (fps.check()) {
                PlayerPacket sendPlayerPacket = new PlayerPacket(player.getX(), player.getY());
                NetworkUtil.sendPlayerPacket(socket, sendPlayerPacket);
//                Thread.sleep(50);
                receivedPlayerPacket = NetworkUtil.readPlayerPacket(socket);
                fps.printFps();
            }
        }
    }

    private void connecToServer() throws IOException {
        socket = new Socket("159.89.99.250", 5000);
        new DataOutputStream(socket.getOutputStream()).writeBoolean(isHost);
        socket.setTcpNoDelay(true);
        System.out.println("Enter name");
        name = scanner.nextLine();
        NetworkUtil.writeString(socket, name);
        if (isHost) {
            System.out.println("Waiting for client");
        }
        String serverMessage = NetworkUtil.readString(socket);
        System.out.println(serverMessage);
        if (serverMessage.equals("Could not find a host")) {
            System.exit(0);
        }
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

    public synchronized PlayerPacket getReceivedPlayerPacket() {
        return receivedPlayerPacket;
    }
}
