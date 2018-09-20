package dto;

import entity.Pet;
import java.io.Serializable;
import java.util.Date;


public class PetDTO  implements Serializable{
    
    public int id;
    public String name;
    public Date birth;
    public String species;
    
    public String ownerFirstName, ownerLastName;

    public PetDTO(Pet pet) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.birth = pet.getBirth();
        this.species = pet.getSpecies();
        
        this.ownerFirstName = pet.getOwner().getFirstName();
        this.ownerLastName = pet.getOwner().getLastName();
    }
    
    

}
