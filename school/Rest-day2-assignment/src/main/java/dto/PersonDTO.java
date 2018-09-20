package dto;

import entity.Address;
import entity.Person;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PersonDTO implements Serializable{

    private int id;
    private String firstName, lastName;
    private List<AddressDTO> addressList = new ArrayList<>();

    public PersonDTO(Person p) {
        this.id = p.getId();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        
        if (p.getAddressList() != null) {
            List<Address> addresses = p.getAddressList();

            for (Address addresse : addresses) {
                addressList.add(new AddressDTO(addresse));
            }
        }
    }

    public List<AddressDTO> getAddressList() {
        return addressList;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }
}
