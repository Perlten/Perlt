package dto.chatRoom;

import dto.user.UserDTO;


public class CreateChatRoomDTO {
    
    public UserDTO owner;
    public String roomName;

    public CreateChatRoomDTO(UserDTO owner, String roomName) {
        this.owner = owner;
        this.roomName = roomName;
    }

}
