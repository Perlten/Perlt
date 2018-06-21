package onlinegameserver;

import java.io.BufferedInputStream;
import onlinegamecommen.PlayerPacket;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import onlinegamecommen.NetworkUtil;

public class OnlineGameServer {

    private HashMap<String, Socket> hostMap;

    public OnlineGameServer() {
        hostMap = new HashMap<>();
        connectHost();
    }

    private void connectHost() {
        try {
            //Connect to host and client
            ServerSocket serverSocket = new ServerSocket(5000);

            while (true) {
                System.out.println("Waiting on host");
                Socket connection = serverSocket.accept();
                boolean isHost = new DataInputStream(connection.getInputStream()).readBoolean();
                String key = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
                System.out.println(key + " is hosting: " + isHost);
                if (isHost) {
                    hostMap.put(key, connection);
                    System.out.println("Host added to map");
                } else {
                    if (hostMap.containsKey(key)) {
                        System.out.println("Game startet");
                        RunGameThread rgt = new RunGameThread(connection, hostMap.get(key));
                        Thread thread = new Thread(rgt);
                        thread.start();
                    } else {
                        System.out.println("Game not startet");
                        NetworkUtil.writeString(connection, "Could not find a host");
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Server error: " + ex.getMessage());
            System.exit(0);
        }

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        OnlineGameServer ogs = new OnlineGameServer();
    }
}
