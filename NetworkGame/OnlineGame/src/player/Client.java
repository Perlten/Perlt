package player;

import gameNetwork.HostNetwork;
import java.awt.Graphics;

public class Client implements Player {

    private int x, y;
    private HostNetwork network;

    public Client(HostNetwork network) {
        this.x = 0;
        this.y = 0;
        this.network = network;
    }

    @Override
    public void update() {
        if (network.getReceivedPlayerPacket() != null) {
            x = network.getReceivedPlayerPacket().getX();
            y = network.getReceivedPlayerPacket().getY();
        }
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(x, y, 25, 25);
    }

}
