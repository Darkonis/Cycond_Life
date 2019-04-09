package edu.se309.app.backend.socket;


import edu.se309.app.backend.rest.entity.Account;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocketServer {

    @Autowired
    private static AccountServiceImplementation accountService;

    private static Map<Account, Session> accountSessionMap = Collections.synchronizedMap(new HashMap<>());
    Account account;
    
    private static Map<Session, Account> sessionAccountMap = Collections.synchronizedMap(new HashMap<>());
    
    private static MonsterServiceImplementation monsterService;
    
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
        sessionAccountMap.put(session, account);
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        Account account = sessionAccountMap.get(session);
        Scanner in = new Scanner(message);
        String command = in.next();
        if(command.equals("COMBAT"))
        {
        	String subCommand = in.next();
    		int id = Integer.parseInt(in.next());
        	if(subCommand.equals("ATTACK")){
        		monsterService.markMonster(id, true);
        	}
        	else if(subCommand.equals("DEFEAT")){
        		monsterService.markMonster(id, false);
        	}
        	else if(subCommand.equals("VICTORY")){
        		monsterService.deleteById(id);
        	}
        	else {
                in.close();
        		throw new IOException();
        	}
        }
        
        if(command.equals("CHAT")) {
        	String remaining = "CHAT " + account.getUsername() + ": ";
        	while(in.hasNext()) {
        		remaining += in.next() + " ";
        	}
        	broadcast(remaining);
        }
        
        else {
            in.close();
        	throw new IOException();
        }
        in.close();
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        accountSessionMap.remove(sessionAccountMap.get(session));
        sessionAccountMap.remove(session);
    }
    
    private static void broadcast(String message) 
  	      throws IOException 
  {	  
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

}