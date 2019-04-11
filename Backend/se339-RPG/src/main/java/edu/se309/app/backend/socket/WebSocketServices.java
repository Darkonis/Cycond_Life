//package edu.se309.app.backend.socket;
//
//import edu.se309.app.backend.rest.entity.Account;
//import edu.se309.app.backend.rest.service.AccountServiceImplementation;
//import edu.se309.app.backend.rest.service.MonsterServiceImplementation;
//
//import javax.websocket.Session;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//public class WebSocketServices {
//
//
//
//    //TODO NOT a good set up for a service so this will need to change
//    public WebSocketServices(String username, Session session,
//                             AccountServiceImplementation accountService,
//                             MonsterServiceImplementation monsterService
//    ) throws IOException {
//        this.monsterService = monsterService;
//        this.accountService = accountService;
//        Account account = accountService.findByUsername(username);
//        if (account == null) {
//            throw new IOException();
//        }
//        this.account = account;
//        this.session = session;
//        accountSessionMap.put(account, session);
//        sessionAccountMap.put(session, account);
//    }
//
