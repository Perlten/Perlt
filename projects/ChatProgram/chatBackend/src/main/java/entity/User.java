package entity;

import dto.user.CreateUserDTO;
import dto.user.UserDTO;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String firstName, lastName;
    @Column(unique = true)
    private String email;
    private int avatar;
    
    private String password;
    
    public User() {
    }

    public User(Integer id, String firstName, String lastName, String email, int avatar) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.avatar = avatar;
    }
    
    public User(CreateUserDTO user){
        this(0, user.firstName, user.lastName, user.email, user.avatar);
        this.password = user.password;
    }
    
    public User(UserDTO dto){
        this(dto.id, dto.firstName, dto.lastName, dto.email, dto.avatar);
    }

    public int getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }
}
