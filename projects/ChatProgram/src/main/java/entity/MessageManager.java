package entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Timer;
import java.util.TimerTask;

public class MessageManager {

    private final int MESSAGEHISTORYSIZE = 10;

    private Message[] messageArr = new Message[MESSAGEHISTORYSIZE];
    private ChatRoom chatRoom;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private Timer timer;

    public MessageManager(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        for (int i = 0; i < MESSAGEHISTORYSIZE; i++) {
            messageArr[i] = new Message("", "");
        }
    }

    public void addMessage(Message message) {
        //Also runs when room is first made because of welcome message
        setTimer();
        
        Message[] temp = new Message[MESSAGEHISTORYSIZE];
        for (int i = 1; i < MESSAGEHISTORYSIZE; i++) {
            temp[i - 1] = messageArr[i];
        }
        temp[MESSAGEHISTORYSIZE - 1] = message;

        messageArr = temp;

        System.out.println("Added");
    }

    public String getJson() {
        String json = gson.toJson(messageArr);
        return json;
    }

    private void setTimer() {
        if(timer != null){
            timer.cancel();
        }
        this.timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                chatRoom = null;
            }
        }, 10 * 60 * 1000);
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public Message[] getMessageArr() {
        return messageArr;
    }

    @Override
    public String toString() {
        return "messageArr=" + messageArr + ", chatRoom=" + chatRoom + ", gson=" + gson + '}';
    }

}
