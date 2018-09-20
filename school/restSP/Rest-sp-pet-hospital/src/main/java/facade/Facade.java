package facade;

import entity.Pet;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;


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
    
    public static void main(String[] args) {
        Facade f = new Facade(Persistence.createEntityManagerFactory("pu"));
        System.out.println(f.getLiveingPet());
    }
    
}
