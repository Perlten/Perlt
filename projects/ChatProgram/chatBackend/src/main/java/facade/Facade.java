package facade;

import dto.chatRoom.CreateChatRoomDTO;
import dto.user.CreateUserDTO;
import dto.user.UserLoginDTO;
import entity.ChatRoom;
import entity.Message;
import entity.User;
import exception.ChatException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Facade {

    private EntityManagerFactory emf;

    public Facade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEM() {
        return emf.createEntityManager();
    }

    //USERS
    public User createUser(CreateUserDTO newUser) {
        User user = new User(newUser);

        EntityManager em = getEM();

        try {
            em.getTransaction().begin();

            em.persist(user);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return user;
    }

    public User userLogin(UserLoginDTO dto) throws ChatException {
        EntityManager em = getEM();

        try {
            TypedQuery<User> q = em.createQuery("SELECT u FROM User u where u.email = :email AND u.password = :password", User.class);
            q.setParameter("email", dto.email);
            q.setParameter("password", dto.password);
            if (q.getResultList().isEmpty()) {
                throw new ChatException("Could not find user", 404);
            }
            return q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<User> getAllUsers() {
        EntityManager em = getEM();
        try {
            Query q = em.createQuery("SELECT u FROM User u");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    //CHATROOM
    public ChatRoom createChatRoom(CreateChatRoomDTO dto) {
        ChatRoom chatRoom = new ChatRoom(dto);
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();

            em.persist(chatRoom);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return chatRoom;
    }

    public ChatRoom getChatRoom(int id) {
        EntityManager em = getEM();
        try {
            return em.find(ChatRoom.class, id);
        } finally {
            em.close();
        }
    }

    public List<Message> getMessageList(String roomName, int amount) {
        EntityManager em = getEM();

        try {
            TypedQuery<ChatRoom> q = em.createQuery("SELECT c FROM ChatRoom c where c.chatRoomName = :roomName", ChatRoom.class);
            q.setParameter("roomName", roomName);
            ChatRoom cm = q.getSingleResult();
            Collections.reverse(cm.getMessagesList());
            List<Message> messageList = new ArrayList<>();

            if (cm.getMessagesList().size() >= amount) {
                for (int i = 0; i < amount; i++) {
                    Message message = cm.getMessagesList().get(i);
                    messageList.add(message);
                }
            } else {
                for (int i = 0; i < cm.getMessagesList().size(); i++) {
                    Message message = cm.getMessagesList().get(i);
                    messageList.add(message);
                }
            }
            return messageList;
        } finally {
            em.close();
        }
    }
    
    public ChatRoom findChatRoomByName(String name) throws ChatException{
        EntityManager em = getEM();
        try{
            TypedQuery<ChatRoom> q = em.createQuery("SELECT c FROM ChatRoom c WHERE c.chatRoomName = :roomName", ChatRoom.class);
            q.setParameter("roomName", name);
            try{
                return q.getSingleResult();
            }catch(NoResultException e){
                throw new ChatException("Could not find room", 404);
            }
        }finally {
            em.close();
        }
    }

    public Message createMessage(Message message, ChatRoom room) {
        EntityManager em = getEM();
//        message.setChatroom(room);
        room.addMessage(message);
        try {
            em.getTransaction().begin();
            em.merge(room);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return message;
    }

}
