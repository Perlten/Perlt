/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import entity.Address;
import entity.Person;
import exceptions.PersonNotFoundExceptionMapper;
import exceptions.PersonNotFoundExceptionMapper;
import facade.PersonFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author perlt
 */
@Path("person")
public class PersonResource {

    @Context
    private UriInfo context;

    private PersonFacade f;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public PersonResource() {
        f = new PersonFacade(Persistence.createEntityManagerFactory("pu"));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPerson(String json) throws PersonNotFoundExceptionMapper {
        try {
            Person p = gson.fromJson(json, Person.class);

            List<Address> aList = p.getAddressList();
            for (Address a : aList) {
                a.setPerson(p);
            }

            f.createPerson(p);

            PersonDTO pdto = new PersonDTO(p);

            String responseJson = gson.toJson(pdto);
            return Response.ok().entity(responseJson).build();
        } catch (Exception e) {
            throw new PersonNotFoundExceptionMapper("Could not post person to server check json syntax");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersons() {
        List<Person> list = f.getPersons();

        List<PersonDTO> dtoList = new ArrayList<>();
        for (Person person : list) {
            dtoList.add(new PersonDTO(person));
        }
        
        String responseJson = gson.toJson(dtoList);
        return Response.ok().entity(responseJson).build();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") int id) throws PersonNotFoundExceptionMapper {
        try {
            Person p = f.getPerson(id);
            PersonDTO dtoP = new PersonDTO(p);
            String responseJson = gson.toJson(dtoP);
            return Response.ok().entity(responseJson).build();
        } catch (Exception e) {
            throw new PersonNotFoundExceptionMapper("Could not find person with id");
        }
    }

}
