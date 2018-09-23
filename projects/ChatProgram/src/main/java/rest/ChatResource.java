/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.ChatRoom;
import entity.Message;
import entity.MessageManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author perlt
 */
@Path("chat")
public class ChatResource {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static Map<String, MessageManager> chatMap = new HashMap<>();

    @Context
    private UriInfo context;

    public ChatResource() {
    }

    @POST
    @Path("{chatRoom}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postMessage(String json, @PathParam("chatRoom") String chatRoom) throws Exception {
        Message message = gson.fromJson(json, Message.class);
        System.out.println(message);
        MessageManager mm = chatMap.get(chatRoom);
        if (mm == null) {
            throw new Exception("Could not find chatroom");
        }
        mm.addMessage(message);
        return Response.ok().build();
    }

    @GET
    @Path("{chatRoom}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessageHistory(@PathParam("chatRoom") String chatRoom) throws Exception {
        MessageManager mm = chatMap.get(chatRoom);
        if (mm == null) {
            throw new Exception();
        }
        String json = mm.getJson();
        System.out.println(json);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createChatRoom(String json) throws Exception {
        ChatRoom chatRoom = gson.fromJson(json, ChatRoom.class);
        if (chatMap.containsKey(chatRoom.getName())) {
            throw new Exception();
        }
        chatMap.put(chatRoom.getName(), new MessageManager(chatRoom));

        System.out.println(chatMap);
        return Response.ok().build();
    }

    @GET
    @Path("publicRooms")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPublicRooms() {
        Set<String> keySet = chatMap.keySet();
        List<ChatRoom> chatRooms = new ArrayList<>();
        for (String key : keySet) {
            ChatRoom cm = chatMap.get(key).getChatRoom();
            if (cm == null) {
                chatMap.remove(key);
            }else if (cm.isPublicRoom()) {
                chatRooms.add(cm);
            }
        }
        String json = gson.toJson(chatRooms);

        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }
}
