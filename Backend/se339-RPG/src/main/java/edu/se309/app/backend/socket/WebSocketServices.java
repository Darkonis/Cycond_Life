package edu.se309.app.backend.socket;

import edu.se309.app.backend.rest.entity.Account;
import edu.se309.app.backend.rest.service.AccountServiceImplementation;
import edu.se309.app.backend.rest.service.MonsterServiceImplementation;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WebSocketServices {

    private static Map<Account, Session> accountSessionMap = Collections.synchronizedMap(new HashMap<>());
    private static Map<Session, Account> sessionAccountMap = Collections.synchronizedMap(new HashMap<>());


    private MonsterServiceImplementation monsterService;

    private AccountServiceImplementation accountService;

    private Account account;
    private Session session;

    //TODO NOT a good set up for a service so this will need to change
    public WebSocketServices(String username, Session session,
                             AccountServiceImplementation accountService,
                             MonsterServiceImplementation monsterService
    ) throws IOException {
        this.monsterService = monsterService;
        this.accountService = accountService;
        Account account = accountService.findByUsername(username);
        if (account == null) {
            throw new IOException();
        }
        this.account = account;
        this.session = session;
        accountSessionMap.put(account, session);
        sessionAccountMap.put(session, account);
    }

    public Account getAccount() {
        return account;
    }

    public Session getSession() {
        return session;
    }

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

    public void COMBAT(String payload) throws IOException {
        int indexOfSplit = payload.indexOf(" ");
        String subCommand = payload.substring(0, indexOfSplit - 1);
        int id = Integer.parseInt(payload.substring(indexOfSplit + 2, payload.length() - 2));
        if (subCommand.equals("ATTACK")) {
            monsterService.markMonster(id, true);
        } else if (subCommand.equals("DEFEAT")) {
            monsterService.markMonster(id, false);
        } else if (subCommand.equals("VICTORY")) {
            monsterService.deleteById(id);
        } else {
            throw new IOException();
        }
    }

    public void CHAT(String payload) {
        String chatMessage = "Chat " + account.getUsername() + ": " + payload;
        broadcast(chatMessage);
    }

    public void destroy() {
        accountSessionMap.remove(sessionAccountMap.get(session));
        sessionAccountMap.remove(session);
    }
}
