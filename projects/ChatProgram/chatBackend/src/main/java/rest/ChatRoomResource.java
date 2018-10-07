/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.chatRoom.ChatRoomDTO;
import dto.chatRoom.CreateChatRoomDTO;
import dto.message.MessageDTO;
import entity.ChatRoom;
import entity.Message;
import exception.ChatException;
import facade.Facade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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
@Path("chatRoom")
public class ChatRoomResource {

    private final Facade facade;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private UriInfo context;

    public ChatRoomResource() {
        this.facade = new Facade(Persistence.createEntityManagerFactory("pu"));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createChatRoom(String json) {
        CreateChatRoomDTO newRoom = gson.fromJson(json, CreateChatRoomDTO.class);
        ChatRoom chatRoom = facade.createChatRoom(newRoom);
        ChatRoomDTO dto = new ChatRoomDTO(chatRoom);
        String responseJson = gson.toJson(dto);
        return Response.ok().entity(responseJson).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChatRoom(@PathParam("id") int id) {
        ChatRoom chatRoom = facade.getChatRoom(id);
        String json = gson.toJson(new ChatRoomDTO(chatRoom));
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("message/receive/{roomName}/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessageList(@PathParam("roomName") String roomName, @PathParam("amount") int amount) {
        List<Message> messageList = facade.getMessageList(roomName, amount);
        List<MessageDTO> dtoList = new ArrayList<>();

        for (Message message : messageList) {
            dtoList.add(new MessageDTO(message));
        }
        String json = gson.toJson(dtoList);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("message/send")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendMessage(String json) throws ChatException {
        MessageDTO dto = gson.fromJson(json, MessageDTO.class);
        ChatRoom chatRoom = facade.findChatRoomByName(dto.chatRoomName);
        Message message = new Message(dto, chatRoom);
        message = facade.createMessage(message, chatRoom);
        dto = new MessageDTO(message);
        String returnJson = gson.toJson(dto);
        return Response.ok().entity(returnJson).type(MediaType.APPLICATION_JSON).build();
    }

  

}
