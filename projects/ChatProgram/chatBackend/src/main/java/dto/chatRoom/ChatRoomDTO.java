    package dto.chatRoom;

import dto.message.MessageDTO;
import dto.user.UserDTO;
import entity.ChatRoom;
import entity.Message;
import java.util.ArrayList;
import java.util.List;

public class ChatRoomDTO {

    public int id;
    public List<MessageDTO> messagesList = new ArrayList<>();
    public String chatRoomName;
    public UserDTO owner;

    public ChatRoomDTO(ChatRoom room) {
        this.id = room.getId();
        this.chatRoomName = room.getChatRoomName();
        this.owner = new UserDTO(room.getOwner());
        for (Message message : room.getMessagesList()) {
            messagesList.add(new MessageDTO(message));
        }
    }

}
