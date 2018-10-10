/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author perlt
 */
@Path("joke")
public class JokeResource {

    private final String[] jokes = {"Haha", "Funnny", "Insert joke"};

    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJoke() {
        String joke = jokes[new Random().nextInt(jokes.length)];
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("joke", joke);
        Gson gson = new Gson();
        String json = gson.toJson(jsonObject);
        System.out.println(json);
        return Response.ok().entity(json)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
