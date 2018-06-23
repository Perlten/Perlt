package onlinegamecommen;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
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
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(object);
        oos.flush();
    }

    public static PlayerPacket readPlayerPacket(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        PlayerPacket pp = (PlayerPacket) ois.readObject();
        return pp;
    }
}
