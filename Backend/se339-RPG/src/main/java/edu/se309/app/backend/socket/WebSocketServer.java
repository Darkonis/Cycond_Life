package edu.se309.app.backend.socket;


import edu.se309.app.backend.rest.controller.AccountController;
import edu.se309.app.backend.rest.entity.Account;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.reflect.*;

import static edu.se309.app.backend.socket.WebSocketSharedSingleton.*;

@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocketServer {

    private Account account;
    private Session session;

    @OnOpen
    public void onOpen(
            Session session,
            @PathParam("username") String username)   {
        AccountController accountController = WebSocketSharedBeans.getAccountController();
        account = accountController.getAccountByUsername(username);
        this.session = session;
        getSessionAccountMap().put(session, account);
        getAccountSessionMap().put(account, session);
    }

    @OnMessage
    @SneakyThrows
    public void onMessage(Session session, String message) throws IOException {
        int indexOfSplit = message.indexOf("(...)");
        if (indexOfSplit < 0) {
            noParametersMethodCall(message);
        } else {
            parameterMethodCall(message, indexOfSplit);
        }



    }

    @SneakyThrows
    private void noParametersMethodCall(String message)  {
        Method method = WebSocketSharedSingleton.getMethod(message.trim());
        if (Modifier.isStatic(method.getModifiers())){
            Object o = method.invoke(null);
            session.getBasicRemote().sendText(method.getReturnType().cast(o).toString());
        } else {
            Object o = WebSocketSharedSingleton.getSavedObject(method.getDeclaringClass().getName());
            method.invoke(method.getReturnType().cast(o));
        }
    }

    @SneakyThrows //TODO
    private void parameterMethodCall(String message, int indexOfSplit) {
        String methodName = message.substring(0, indexOfSplit).trim();
        String[] parameters = message.substring(indexOfSplit + 5).split("-,-");
        Method method = WebSocketSharedSingleton.getMethod(methodName);
        Class[] parametersClasses = method.getParameterTypes();

        for (Class c: parametersClasses){

        }
}

    @OnClose
    public void onClose(){
        this.destroy();
    }

    public Account getAccount() {
        return account;
    }

    public Session getSession() {
        return session;
    }

//    public void COMBAT(String payload) throws IOException {
//        int indexOfSplit = payload.indexOf(" ");
//        String subCommand = payload.substring(0, indexOfSplit - 1);
//        int id = Integer.parseInt(payload.substring(indexOfSplit + 2, payload.length() - 2));
//        if (subCommand.equals("ATTACK")) {
//            monsterService.markMonster(id, true);
//        } else if (subCommand.equals("DEFEAT")) {
//            monsterService.markMonster(id, false);
//        } else if (subCommand.equals("VICTORY")) {
//            monsterService.deleteById(id);
//        } else {
//            throw new IOException();
//        }
//    }


    public void destroy() {
        getAccountSessionMap().remove(getSessionAccountMap().get(session));
        getSessionAccountMap().remove(session);
    }
}