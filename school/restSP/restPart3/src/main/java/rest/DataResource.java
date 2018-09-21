/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author perlt
 */
@Path("data")
public class DataResource {
    
    @Context
    private UriInfo context;

    @GET
    @Path("{amount}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getData(@PathParam("amount") int amount, @PathParam("id") int id){
        String[] jsonArr = new generator.Generator(amount, id).genJsonArr();
        
        String json = "[";
        json += System.lineSeparator();
        for (String string : jsonArr) {
            json += string;
            json += System.lineSeparator();
        }
        json += "]";
        
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }
 
}
