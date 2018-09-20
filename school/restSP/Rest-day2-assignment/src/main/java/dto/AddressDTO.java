package dto;

import entity.Address;
import java.io.Serializable;


public class AddressDTO implements Serializable{
    
    private String city, street;
    private int personId;

    public AddressDTO(Address address) {
        this.city = address.getCity();
        this.street = address.getStreet();
        this.personId = address.getPerson().getId();
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }
}
