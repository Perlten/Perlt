package player;

import gameNetwork.Network;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import onlinegamecommen.PlayerPacket;

public class Client implements Player {

    private int x, y;

    private Network network;

    private PlayerPacket lastPP;
    private List<PlayerPacket> packetList;

    private Rectangle hitbox;
    
    public Client(Network network) {
        this.x = 275;
        this.y = 275;
        hitbox = new Rectangle(x, y, 25, 25);
        this.packetList = new ArrayList<>();
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

            packetList.add(newPP);
        } else {
            predict();
        }
        hitbox.setLocation(x, y);
        if(packetList.size() >= 100){
            PlayerPacket temp1 = packetList.get(packetList.size() - 1);
            PlayerPacket temp2 = packetList.get(packetList.size() - 2);
            packetList.clear();
            packetList.add(temp1);
            packetList.add(temp2);
        }
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(x, y, 25, 25);
    }

    private void predict() {
        if (packetList.size() < 2) {
            return;
        }
        int vertical = y - packetList.get(packetList.size() - 2).getY();
        int hori = x - packetList.get(packetList.size() - 2).getX();

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

    public Rectangle getHitbox() {
        return hitbox;
    }
}
