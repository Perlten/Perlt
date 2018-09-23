package entity;


public class Message {
    
    private String message;
    private String name;

    public Message(String message, String name) {
        this.message = message;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Message{" + "message=" + message + ", name=" + name + '}';
    }
    
}
