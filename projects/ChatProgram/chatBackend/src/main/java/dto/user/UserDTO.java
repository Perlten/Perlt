package dto.user;

import entity.User;

public class UserDTO {

    public Integer id;

    public String firstName, lastName;

    public String email;

    public int avatar;

    public UserDTO(User user) {
        this.avatar = user.getAvatar();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.id =user.getId();
    }

}
