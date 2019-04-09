package edu.se309.app.backend.websocket.controller;

import edu.se309.app.backend.websocket.entity.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendGlobal")
    @SendTo("/topic/public")
    public ChatMessage handle(@Payload ChatMessage chatMessage) {
        Date currentTime = new Date();
        chatMessage.setTimeStamp(currentTime);
        //TODO add persistence?
        return chatMessage;
    }
}
