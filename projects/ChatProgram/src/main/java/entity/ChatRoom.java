package entity;


public class ChatRoom {

    private String name, owner;
    private boolean publicRoom;

    public ChatRoom(String name, String owner, boolean publicRoom) {
        this.name = name;
        this.owner = owner;
        this.publicRoom = publicRoom;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isPublicRoom() {
        return publicRoom;
    }

    @Override
    public String toString() {
        return "ChatRoom{" + "name=" + name + ", owner=" + owner + ", publicRoom=" + publicRoom + '}';
    }

}
