package onlinegameserver;

import onlinegamecommen.PlayerPacket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import onlinegamecommen.NetworkUtil;

public class OnlineGameServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        //Host
        Socket host;
        String hostName;
        
        //Client
        Socket client;
        String clientName;
        
        //Connect to host and client
        ServerSocket serverSocket = new ServerSocket(5000);
        
        System.out.println("Waiting on host");
        host = serverSocket.accept();
        System.out.println("Host connected");
        hostName = new BufferedReader(new InputStreamReader(host.getInputStream())).readLine();
        System.out.println("Host name: " + hostName);
        
        System.out.println("Waiting on client");
        client = serverSocket.accept();
        System.out.println("Client connected");
        clientName = new BufferedReader(new InputStreamReader(client.getInputStream())).readLine();
        System.out.println("Client name: " + clientName);
        
        new PrintWriter(host.getOutputStream(), true).println(clientName + " connected");
        new PrintWriter(client.getOutputStream(), true).println(hostName + " connected");
        
        //Loop to send data
        while (true) {            
            //Read client object.
            byte[] clientBuffer = NetworkUtil.readBuffer(client);
            NetworkUtil.sendBuffer(host, clientBuffer);
            
             //Read host object.
            byte[] hostBuffer = NetworkUtil.readBuffer(host);
            NetworkUtil.sendBuffer(client, hostBuffer);
        }
        
    }
}
