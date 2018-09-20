package facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Event;
import entity.Pet;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class Facade {
    
    private EntityManagerFactory emf;

    public Facade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    
    public List<Pet> getAllPets(){
        EntityManager em = getEntityManager();
        
        try{
            return em.createNamedQuery("Pet.findAll").getResultList();
        }finally {
            em.close();
        }
        
    }
    
    
    public List<Pet> getLiveingPet(){
        EntityManager em = getEntityManager();
        try{
            Query q = em.createQuery("SELECT p FROM Pet p WHERE p.death <= :death");
            Date date = new Date();
            
            q.setParameter("death", new Date());
            return q.getResultList();
        }finally {
            em.close();
        }
    }
    
    public List<Pet> eventDatePetList(Date date){
         EntityManager em = getEntityManager();
        try{
            Query q = em.createQuery("SELECT p FROM Pet p where p.id = (SELECT e.pet.id FROM Event e WHERE e.date = :date)");
            q.setParameter("date", date);
            return q.getResultList();
        }finally {
            em.close();
        }
    }
    
    public Event createEventForPet(Event event, int petId){
         EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            Pet pet = em.find(Pet.class, petId);
            pet.addEvent(event);
            event.setPet(pet);
            em.persist(event);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return event;
    }
    
    public static void main(String[] args) {
        Facade f = new Facade(Persistence.createEntityManagerFactory("pu"));
        
        Pet pet = f.getAllPets().get(0);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Event event = pet.getEventCollection().get(0);
        event.setPet(null);
        String json = gson.toJson(event);
        
        System.out.println(json);
    }
    
    
}
