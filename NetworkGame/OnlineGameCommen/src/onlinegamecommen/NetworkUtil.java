package onlinegamecommen;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkUtil {

    public static String readString(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
    }

    public static void writeString(Socket socket, String message) throws IOException {
        new PrintWriter(socket.getOutputStream(), true).println(message);
    }

    public static void sendPlayerPacket(Socket socket, Object object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bos);
        out.writeObject(object);
        out.flush();

        byte[] buffer = bos.toByteArray();
        BufferedOutputStream dos = new BufferedOutputStream(socket.getOutputStream());
        dos.write(buffer.length);
        dos.write(buffer);
        dos.flush();
    }

    public static PlayerPacket readPlayerPacket(Socket socket) throws IOException, ClassNotFoundException {
        BufferedInputStream dis = new BufferedInputStream(socket.getInputStream());
        int bufferSize = dis.read();
        byte[] reveiveBuffer = new byte[bufferSize];
        dis.read(reveiveBuffer);

        ObjectInput oi = new ObjectInputStream(new ByteArrayInputStream(reveiveBuffer));
        PlayerPacket playerPacket = (PlayerPacket) oi.readObject();
        return playerPacket;
    }

    public static void sendBuffer(Socket socket, byte[] buffer) throws IOException {
        BufferedOutputStream dos = new BufferedOutputStream(socket.getOutputStream());
        dos.write(buffer.length);
        dos.write(buffer);
        dos.flush();
    }

    public static byte[] readBuffer(Socket socket) throws IOException {
        BufferedInputStream dis = new BufferedInputStream(socket.getInputStream());
        int bufferSize = dis.read();
        byte[] buffer = new byte[bufferSize];
        dis.read(buffer);
        return buffer;
    }
}
