package entity;

import dto.chatRoom.ChatRoomDTO;
import dto.chatRoom.CreateChatRoomDTO;
import dto.message.MessageDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;


@Entity
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "chatroom")
    @JoinColumn
    private List<Message> messagesList = new ArrayList<>();
    @Column(unique = true)
    private String chatRoomName;
    @OneToOne
    private User owner;

    public ChatRoom() {
    }

    public ChatRoom(CreateChatRoomDTO dto) {
        this.chatRoomName = dto.roomName;
        this.owner = new User(dto.owner);
    }
    
    public ChatRoom(ChatRoomDTO dto){
        this.id = dto.id;
        this.chatRoomName = dto.chatRoomName;
        this.owner = new User(dto.owner);
        for (MessageDTO messageDTO : dto.messagesList) {
            messagesList.add(new Message(messageDTO, this));
        }
    }
    
    public void addMessage(Message message){
        messagesList.add(message);
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public List<Message> getMessagesList() {
        return messagesList;
    }

    public User getOwner() {
        return owner;
    }
    
    public Integer getId() {
        return id;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public void setMessagesList(List<Message> messagesList) {
        this.messagesList = messagesList;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    
}
