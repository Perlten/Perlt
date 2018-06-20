package player;

import gameNetwork.HostNetwork;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import onlinegamecommen.PlayerPacket;

public class Client implements Player {

    private int x, y;

    private HostNetwork network;

    private PlayerPacket lastPP;
    private List<Integer> cordinateListX;
    private List<Integer> cordinateListY;

    public Client(HostNetwork network) {
        this.x = 0;
        this.y = 0;
        this.cordinateListX = new ArrayList<>();
        this.cordinateListY = new ArrayList<>();
        this.network = network;
    }

    @Override
    public void update() {
        PlayerPacket newPP = network.getReceivedPlayerPacket();
        if (newPP == null) {
            return;
        }

        if (lastPP == null || lastPP.getId() != newPP.getId()) {
            lastPP = newPP;
            x = newPP.getX();
            y = newPP.getY();

            cordinateListX.add(x);
            cordinateListY.add(y);
        } else {
            predict();
        }
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(x, y, 25, 25);
    }

    private void predict() {
        if (cordinateListX.size() < 2) {
            return;
        }
        int vertical = y - cordinateListY.get(cordinateListY.size() - 2);
        int hori = x - cordinateListX.get(cordinateListX.size() - 2);

        if (vertical > 0) {
            y += 3;
        } else if (vertical < 0) {
            y -= 3;
        }
        if (hori > 0) {
            x += 3;
        } else if (hori < 0) {
            x -= 3;
        }
    }

}
