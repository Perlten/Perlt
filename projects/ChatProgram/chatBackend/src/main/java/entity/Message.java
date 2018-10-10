package entity;

import dto.message.MessageDTO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String message;
    @OneToOne
    private User sender;
    
    @ManyToOne
    private ChatRoom chatroom;

    public Message() {
    }
    
    public Message(MessageDTO dto, ChatRoom room){
        this.id = dto.id;
        this.message = dto.message;
        this.sender = new User(dto.sender);
        this.chatroom = room;
    }

    public Message(Integer id, String message, User sender, ChatRoom chatRoom) {
        this.id = id;
        this.message = message;
        this.sender = sender;
        this.chatroom = chatRoom;
    }

    public String getMessage() {
        return message;
    }

    public User getSender() {
        return sender;
    }
    
    public Integer getId() {
        return id;
    }

    public ChatRoom getChatroom() {
        return chatroom;
    }

    public void setChatroom(ChatRoom chatroom) {
        this.chatroom = chatroom;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
