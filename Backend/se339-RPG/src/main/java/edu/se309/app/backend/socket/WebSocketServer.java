package edu.se309.app.backend.socket;


import edu.se309.app.backend.rest.service.AccountServiceImplementation;
import edu.se309.app.backend.rest.service.MonsterServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.reflect.Method;

@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocketServer {

    private WebSocketServices webSocketServices;
    private AccountServiceImplementation accountService;
    private MonsterServiceImplementation monsterService;

    @Autowired
    public void setAccountService(AccountServiceImplementation accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setMonsterService(MonsterServiceImplementation monsterService) {
        this.monsterService = monsterService;
    }

    @OnOpen
    public void onOpen(
            Session session,
            @PathParam("username") String username) throws IOException {
        webSocketServices = new WebSocketServices(username, session, accountService, monsterService);
    }


    public WebSocketServices getWebSocketServices() {
        return webSocketServices;
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        int indexOfSplit = message.indexOf(" ");
        String methodName = message.substring(0, indexOfSplit - 1);
        String payload = message.substring(indexOfSplit + 1);
        try {
            Method method = webSocketServices.getClass().getMethod(methodName, String.class);
            method.invoke(payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        webSocketServices.destroy();
    }


}
