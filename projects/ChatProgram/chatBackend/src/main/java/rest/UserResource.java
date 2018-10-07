/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.user.CreateUserDTO;
import dto.user.UserDTO;
import dto.user.UserLoginDTO;
import entity.User;
import exception.ChatException;
import facade.Facade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author perlt
 */
@Path("user")
public class UserResource {

    private final Facade facade;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private UriInfo context;

    public UserResource() {
        this.facade = new Facade(Persistence.createEntityManagerFactory("pu"));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(String json) {
        CreateUserDTO newUser = gson.fromJson(json, CreateUserDTO.class);
        User user = facade.createUser(newUser);
        UserDTO userDTO = new UserDTO(user);
        String responseJson = gson.toJson(userDTO);
        return Response.ok().entity(responseJson).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<UserDTO> dtoList = new ArrayList<>();
        List<User> userList = facade.getAllUsers();
        for (User user : userList) {
            dtoList.add(new UserDTO(user));
        }
        String json = gson.toJson(dtoList);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    public static void main(String[] args) {
        Persistence.generateSchema("pu", null);
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userLogin(String json) throws ChatException {
        UserLoginDTO dto = gson.fromJson(json, UserLoginDTO.class);
        User user = facade.userLogin(dto);
        String responseJson = gson.toJson(new UserDTO(user));
        return Response.ok().entity(responseJson).type(MediaType.APPLICATION_JSON).build();
    }
}
