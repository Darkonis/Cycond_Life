package edu.se309.app.backend.socket;


import com.google.gson.Gson;
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
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static edu.se309.app.backend.socket.WebSocketSharedSingleton.getAccountSessionMap;
import static edu.se309.app.backend.socket.WebSocketSharedSingleton.getSessionAccountMap;

@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocketServer {

    private Account account;
    private Session session;

    /**
     * Sets up and starts a websocket connection with the front end. Links the websocket up with an account based on the given username
     *
     * @param session  The websocket session
     * @param username Username of the account
     */
    @OnOpen
    public void onOpen(
            Session session,
            @PathParam("username") String username) {
        AccountController accountController = WebSocketSharedBeans.getAccountController();
        account = accountController.getAccountByUsername(username);
        this.session = session;
        getSessionAccountMap().put(session, account);
        getAccountSessionMap().put(account, session);
    }

    /**
     * Listens for messages and then directs the request to appropriate method
     *
     * @param session The websocket's sessions
     * @param message The message in the format "{full method name with parameter types}(...){parameter 1}-,-{parameter 2}-,-{parameter...}"
     * @throws IOException
     */
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

    /**
     * Used for directing messages that require a method without a parameter
     *
     * @param message the message to be redirected. The message should only contain the full method name
     */
    @SneakyThrows
    public void noParametersMethodCall(String message) {
        Method method = WebSocketSharedSingleton.getMethod(message.trim());
        if (Modifier.isStatic(method.getModifiers())) {
            Object o = method.invoke(null);
            session.getBasicRemote().sendText(method.getReturnType().cast(o).toString());
        } else {
            Object o = WebSocketSharedSingleton.getSavedObject(method.getDeclaringClass().getName());
            method.invoke(method.getReturnType().cast(o));
        }
    }

    /**
     * Used for directing messages that require a method with parameter values
     *
     * @param message      The message in the format "{full method name with parameter types}(...){parameter 1}-,-{parameter 2}-,-{parameter...}"
     * @param indexOfSplit the index where the parameters begin
     */
    @SneakyThrows
    public void parameterMethodCall(String message, int indexOfSplit) {
        String methodName = message.substring(0, indexOfSplit).trim();
        String[] parameters = message.substring(indexOfSplit + 5).split("-,-");
        Method method = WebSocketSharedSingleton.getMethod(methodName);
        Class[] parametersClasses = method.getParameterTypes();
        List<Object> parameterValues = new ArrayList<>();
        for (int i = 0; i < parametersClasses.length; i++) {
            if (parametersClasses[i].isPrimitive()) {
                if (parametersClasses[i] == int.class) {
                    parameterValues.add(Integer.parseInt(parameters[i]));
                } else if (parametersClasses[i] == boolean.class) {
                    parameterValues.add(Boolean.parseBoolean(parameters[i]));
                } else if (parametersClasses[i] == byte.class) {
                    parameterValues.add(Byte.parseByte(parameters[i]));
                } else if (parametersClasses[i] == short.class) {
                    parameterValues.add(Short.parseShort(parameters[i]));
                } else if (parametersClasses[i] == long.class) {
                    parameterValues.add(Long.parseLong(parameters[i]));
                } else if (parametersClasses[i] == float.class) {
                    parameterValues.add(Float.parseFloat(parameters[i]));
                } else if (parametersClasses[i] == double.class) {
                    parameterValues.add(Double.parseDouble(parameters[i]));
                }
            } else {
                Gson gson = new Gson();
                Object o = gson.fromJson(parameters[i], parametersClasses[i]);
                parameterValues.add(o);
            }
        }
        if (Modifier.isStatic(method.getModifiers())) {
            Object o = method.invoke(null, parameterValues);
            session.getBasicRemote().sendText(method.getReturnType().cast(o).toString());
        } else {
            Object o = WebSocketSharedSingleton.getSavedObject(method.getDeclaringClass().getName());
            method.invoke(method.getReturnType().cast(o), parameterValues);
        }

    }

    /**
     * Closes the websocket
     */
    @OnClose
    public void onClose() {
        this.destroy();
    }

    /**
     * Returns the account associated with this websocket
     *
     * @return the account associated with this websocket
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Returns the session associated with this websocket
     *
     * @return the session associated with this websocket
     */
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


    /**
     * Removes the websocket from the account and session maps
     */
    public void destroy() {
        getAccountSessionMap().remove(getSessionAccountMap().get(session));
        getSessionAccountMap().remove(session);
    }
}