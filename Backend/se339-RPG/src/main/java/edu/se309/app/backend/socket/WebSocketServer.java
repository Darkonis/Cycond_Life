package edu.se309.app.backend.socket;


import edu.se309.app.backend.rest.entity.Account;
import edu.se309.app.backend.rest.service.interfaces.AccountService;
import edu.se309.app.backend.rest.service.interfaces.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import edu.se309.app.backend.rest.entity.Account;
import edu.se309.app.backend.rest.service.AccountServiceImplementation;
import edu.se309.app.backend.rest.service.MonsterServiceImplementation;
import edu.se309.app.backend.rest.service.interfaces.AccountService;
import edu.se309.app.backend.rest.service.interfaces.MonsterService;
import edu.se309.app.backend.common.config.CustomConfigurator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ServerEndpoint(value = "/websocket/{username}")
@Component
public class WebSocketServer {

    private static Map<Account, Session> accountSessionMap = Collections.synchronizedMap(new HashMap<>());
    private static Map<Session, Account> sessionAccountMap = Collections.synchronizedMap(new HashMap<>());

    //Autowiring manually. Hot fix for the moment
    private MonsterService monsterService = WebSocketAutoWire.getBean(MonsterService.class);
    private AccountService accountService = WebSocketAutoWire.getBean(AccountService.class);

    private Account account;
    private Session session;

    public static Map<Account, Session> getAccountSessionMap() {
        return accountSessionMap;
    }

    public static Map<Session, Account> getSessionAccountMap() {
        return sessionAccountMap;
    }

    private static void broadcast(String message) {
        sessionAccountMap.forEach((session, account) -> {
            synchronized (session) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setMonsterService(MonsterService monsterService) {
        this.monsterService = monsterService;
    }

    @OnOpen
    public void onOpen(
            Session session,
            @PathParam("username") String username) throws IOException {
        try {
            this.account = accountService.findByUsername(username);
            this.session = session;
        } catch (NullPointerException e) {
            throw new IOException("The username: " + username + " was not found");
        }
        sessionAccountMap.put(session, account);
        accountSessionMap.put(account, session);
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        int indexOfSplit = message.indexOf(" ");
        String methodName;
        String payload = null;
        if (indexOfSplit > 0) {
            methodName = message.substring(0, indexOfSplit);
            payload = message.substring(indexOfSplit + 1);
        } else {
            methodName = message;
        }
        try {
            if (payload == null) {
                Method method = WebSocketServer.class.getMethod(methodName);
                method.invoke(this);
            } else {
                Method method = WebSocketServer.class.getMethod(methodName, String.class);
                method.invoke(this, payload);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Message received did not map to a valid method");
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        this.destroy();
    }

    public Account getAccount() throws IOException {
        session.getBasicRemote().sendText(account.toString());
        return account;
    }

    public Session getSession() throws IOException {
        session.getBasicRemote().sendText(session.toString());
        return session;
    }

    public void COMBAT(String payload) throws IOException {
    	Scanner in = new Scanner(payload);
        String subCommand = in.next();
        int id = Integer.parseInt(in.next());
        if (subCommand.equals("ATTACK")) {
            monsterService.markMonster(id, true);
            broadcast("REFRESH");
            broadcast("CHAT Player: " + account.getUsername() + " attacked Monster " + id);
        } else if (subCommand.equals("DEFEAT")) {
            monsterService.markMonster(id, false);
            broadcast("REFRESH");
            broadcast("CHAT Player: " + account.getUsername() + " was defeated by Monster " + id);
        } else if (subCommand.equals("VICTORY")) {
            monsterService.deleteById(id);
            broadcast("REFRESH");
            broadcast("CHAT Player: " + account.getUsername() + " defeated Monster " + id);
        } else {
        	in.close();
            throw new IOException();
        }
        in.close();
    }

    public void CHAT(String payload) {
        String chatMessage = "CHAT Chat " + account.getUsername() + ": " + payload;
        broadcast(chatMessage);
    }

    public void DIRECT_MESSAGE(String payload) throws IOException {
        int indexOfSplit = payload.indexOf(" ");
        Account receivingUser = accountService.findByUsername(payload.substring(0, indexOfSplit));
        Session receivingSession = accountSessionMap.get(receivingUser);
        String chatMessage = "DM " + account.getUsername() + ": " + payload.substring(indexOfSplit + 1);
        receivingSession.getBasicRemote().sendText(chatMessage);
    }

    @Scheduled(fixedRate = 6000)
    public void PingClientForGeo() throws IOException {
        broadcast("GEO");
    }

    public void destroy() {
        accountSessionMap.remove(sessionAccountMap.get(session));
        sessionAccountMap.remove(session);
    }
}