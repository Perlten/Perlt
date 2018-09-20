package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.*;


@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @JoinColumn(name = "PERSONID")
    @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST)
    private List<Address> addressList;
    
    private String firstName, lastName;

    public Person() {
    }
    
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public void addAddress(Address address){
        if (addressList == null){
            addressList = new ArrayList<>();
        }
        addressList.add(address);
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

    public List<Address> getAddressList() {
        return addressList;
    }
    
    public static void main(String[] args) {
        Persistence.generateSchema("pu", null);
    }
}
