package dto.message;

import dto.chatRoom.ChatRoomDTO;
import dto.user.UserDTO;
import entity.Message;


public class MessageDTO {
    
    public int id;
    
    public String message;
    
    public UserDTO sender;

    public String chatRoomName;
    
    public MessageDTO(Message message) {
        this.id = message.getId();
        this.message = message.getMessage();
        this.sender = new UserDTO(message.getSender());
        this.chatRoomName = message.getChatroom().getChatRoomName();
    }

}
