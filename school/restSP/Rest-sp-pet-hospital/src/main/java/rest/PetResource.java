/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PetDTO;
import entity.Pet;
import facade.Facade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("pet")
public class PetResource {

    @Context
    private UriInfo context;
    private Facade f;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public PetResource() {
        f = new Facade(Persistence.createEntityManagerFactory("pu"));
    }

    @GET
    @Path("size")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPetSize() {
        int size = f.getAllPets().size();
        String json = "{\"size\" :" + size + "}";

        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPets() {
        List<Pet> petList = f.getAllPets();
        List<PetDTO> dto = convertPetList(petList);

        String json = gson.toJson(dto);

        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();

    }
    
    @GET
    @Path("dead")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLiveingPets() {
        
        List<Pet> petList = f.getLiveingPet();
        List<PetDTO> pto = convertPetList(petList);
        
        String json = gson.toJson(pto);
        
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    private List<PetDTO> convertPetList(List<Pet> petList) {
        List<PetDTO> dto = new ArrayList<>();
        for (Pet pet : petList) {
            dto.add(new PetDTO(pet));
        }
        return dto;
    }

}