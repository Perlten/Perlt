/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinegameserver;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import onlinegamecommen.NetworkUtil;

/**
 *
 * @author Perlt
 */
public class RunGameThread implements Runnable {

    Socket host;
    Socket client;

    public RunGameThread(Socket host, Socket client) {
        this.host = host;
        this.client = client;
    }

    @Override
    public void run() {
        try {

            NetworkUtil.writeString(host, "Client connected");
            NetworkUtil.writeString(client, "Host connected");

            //Loop to send data
            while (true) {
                //Read client packet.
                byte[] clientBuffer = NetworkUtil.readBuffer(client);
                NetworkUtil.sendBuffer(host, clientBuffer);

                //Read host packet.
                byte[] hostBuffer = NetworkUtil.readBuffer(host);
                NetworkUtil.sendBuffer(client, hostBuffer);
            }
        } catch (IOException ex) {
            try {
                System.out.println("Game disconnect");
                client.close();
                host.close();
            } catch (IOException ex1) {
                System.out.println("Could not close connection");
            }
        }
    }

}
