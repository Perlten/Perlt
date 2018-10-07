package dto.user;

public class CreateUserDTO {

    public String firstName, lastName;

    public String email;

    public int avatar;
    
    public String password;

    public CreateUserDTO(String firstName, String lastName, String email, int avatar, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.avatar = avatar;
        this.password = password;
    }

}
