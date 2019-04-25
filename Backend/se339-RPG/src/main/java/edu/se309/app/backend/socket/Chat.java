package edu.se309.app.backend.socket;

import edu.se309.app.backend.rest.entity.Account;

import javax.websocket.Session;
import java.io.IOException;

import static edu.se309.app.backend.socket.WebSocketSharedBeans.getAccountController;
import static edu.se309.app.backend.socket.WebSocketSharedSingleton.broadcast;
import static edu.se309.app.backend.socket.WebSocketSharedSingleton.getAccountSessionMap;

/**
 * Class for chat for the websocket
 */
public class Chat {


    /**
     * Sends a global message with the format "Chat {Username of sender} : {message being sent}"
     *
     * @param userSendingMessage username of sender
     * @param globalMessage      message to be broadcasted
     */
    public void CHAT(String userSendingMessage, String globalMessage) {
        String chatMessage = "Chat " + userSendingMessage + ": " + globalMessage;
        broadcast(chatMessage);
    }

    /**
     * Sends a message to a particular user with the format "DM {Username of sender} : {message being sent}"
     *
     * @param toUser   username of the receiving account
     * @param fromUser username of the account sending the message
     * @param message  message to be sent
     * @throws IOException
     */
    public void DIRECT_MESSAGE(String toUser, String fromUser, String message) throws IOException {
        Account receivingUser = getAccountController().getAccountByUsername(toUser);
        Session receivingSession = getAccountSessionMap().get(receivingUser);
        String chatMessage = "DM " + fromUser + ": " + message;
        receivingSession.getBasicRemote().sendText(chatMessage);
    }
}
