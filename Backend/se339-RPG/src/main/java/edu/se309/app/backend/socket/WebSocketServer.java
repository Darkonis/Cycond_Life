package edu.se309.app.backend.socket;


import edu.se309.app.backend.rest.entity.Account;
import edu.se309.app.backend.rest.service.AccountServiceImplementation;
import edu.se309.app.backend.rest.service.MonsterServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Vamsi Krishna Calpakkam
 *
 */
@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocketServer {
	
    //@Autowired
    //private static AccountServiceImplementation accountService;

    private static Map<String, Session> accountSessionMap = Collections.synchronizedMap(new HashMap<>());
    Account account;
    
    private static Map<Session, String> sessionAccountMap = Collections.synchronizedMap(new HashMap<>());
    
    //@Autowired
    //private static MonsterServiceImplementation monsterService;
    
    //public static AccountServiceImplementation getAccountService() {
      //  return accountService;
    //}
	// Store all socket session and their corresponding username.
    private static Map<Session, String> sessionUsernameMap = new HashMap<>();
    private static Map<String, Session> usernameSessionMap = new HashMap<>();
    
    private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    
    @OnOpen
    public void onOpen(
    	      Session session, 
    	      @PathParam("username") String username) throws IOException 
    {
        logger.info("Entered into Open");
        
        sessionUsernameMap.put(session, username);
        usernameSessionMap.put(username, session);
        accountSessionMap.put(username, session);
        sessionAccountMap.put(session, username);
        
        String message="User:" + username + " has Joined the Chat";
        	broadcast(message);
		
    }
    
    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        String username = sessionAccountMap.get(session);
        Scanner in = new Scanner(message);
        String command = in.next();
        if(command.equals("Combat"))
        {
        	String subCommand = in.next();
    		int id = Integer.parseInt(in.next());
        	if(subCommand.equals("Attack")){
        		//monsterService.markMonster(id, true);
        		broadcast("User: " + username + " attacked Monster " + id);
        	}
        	else if(subCommand.equals("Defeat")){
        		//monsterService.markMonster(id, false);
        	}
        	else if(subCommand.equals("Victory")){
        		//monsterService.deleteById(id);
        	}
        	else {
                in.close();
        		throw new IOException();
        	}
        }
        else if(command.contentEquals("CHAT")) {
        	String remaining = in.next();
        	if(remaining.startsWith("@")) {
        		String newUsername = remaining.substring(1);
        		String newMessage = "";
        		while(in.hasNext())
        		{
        			newMessage += in.next() + " ";
        		}
        		sendMessageToParticularUser(newUsername,newMessage);
        	}
        	while(in.hasNext())
        	{
        		remaining +=" " + in.next();
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
    public void onClose(Session session) throws IOException
    {
    	logger.info("Entered into Close");
    	
    	String username = sessionUsernameMap.get(session);
    	sessionUsernameMap.remove(session);
    	usernameSessionMap.remove(username);
        
    	String message= username + " disconnected";
        broadcast(message);
    }
 
    @OnError
    public void onError(Session session, Throwable throwable) 
    {
        // Do error handling here
    	logger.info("Entered into Error");
    }
    
	private void sendMessageToParticularUser(String username, String message) 
    {	
    	try {
    		usernameSessionMap.get(username).getBasicRemote().sendText(message);
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    private static void broadcast(String message) 
    	      throws IOException 
    {	  
    	sessionUsernameMap.forEach((session, username) -> {
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
