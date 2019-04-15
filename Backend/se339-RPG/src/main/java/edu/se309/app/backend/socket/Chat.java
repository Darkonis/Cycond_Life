package edu.se309.app.backend.socket;

import edu.se309.app.backend.rest.entity.Account;

import javax.websocket.Session;
import java.io.IOException;

import static edu.se309.app.backend.socket.WebSocketSharedBeans.getAccountController;
import static edu.se309.app.backend.socket.WebSocketSharedSingleton.broadcast;
import static edu.se309.app.backend.socket.WebSocketSharedSingleton.getAccountSessionMap;

public class Chat {


    public Chat (){

    }

    public void CHAT(String userSendingMessage, String globalMessage) {
        String chatMessage = "Chat " + userSendingMessage + ": " + globalMessage;
        broadcast(chatMessage);
    }

    public void DIRECT_MESSAGE(String toUser, String fromUser, String message) throws IOException {
        Account receivingUser = getAccountController().getAccountByUsername(toUser);
        Session receivingSession = getAccountSessionMap().get(receivingUser);
        String chatMessage = "DM " + fromUser + ": " + message;
        receivingSession.getBasicRemote().sendText(chatMessage);
    }
}
