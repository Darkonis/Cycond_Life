package edu.se309.app.backend.socket;


import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.service.AccountServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocketServer {

    @Autowired
    private static AccountServiceImplementation accountService;

    private static Map<Account, Session> accountSessionMap = Collections.synchronizedMap(new HashMap<>());
    Account account;

    public static AccountServiceImplementation getAccountService() {
        return accountService;
    }

    public Account getAccount() {
        return account;
    }

    @OnOpen
    public void onOpen(
            Session session,
            @PathParam("username") String username) throws IOException {
        account = accountService.findByUsername(username);
        accountSessionMap.put(account, session);
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        //TODO
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        accountSessionMap.remove(account);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        //Error handling? Huh what. This is a foreign concept. Everything works perfectly.
    }



}
