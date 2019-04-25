package edu.se309.app.backend.socket;


import edu.se309.app.backend.rest.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * A class to keep track of shared objects and methods for the websocket
 */
@Component
public class WebSocketSharedSingleton {

    private static Map<String, Method> methodMap = Collections.synchronizedMap(new HashMap<>());
    private static Map<String, Object> singletonObjectMap = Collections.synchronizedMap(new HashMap<>());
    private static WebSocketSharedSingleton sharedSingleton;

    private static Map<Account, Session> accountSessionMap = Collections.synchronizedMap(new HashMap<>());
    private static Map<Session, Account> sessionAccountMap = Collections.synchronizedMap(new HashMap<>());

    private WebSocketSharedSingleton() throws Exception {
        initialHashMaps();

    }

    /**
     * Creates or returns a singleton object used to store common objects used by the web socket as well as their associated methods
     *
     * @return the common WebSocketSharedSingleton
     */
    @Autowired
    public static WebSocketSharedSingleton webSocketSharedSingleton() {
        if (sharedSingleton == null) {
            try {
                return new WebSocketSharedSingleton();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
                //Shouldn't happen
            }
        } else {
            return sharedSingleton;
        }
    }

    //Creates an object from every parameters and stores it in a static hashMap
    //Also calls addClassMethods to add all the methods to methodMap
    //May want to implement annotations to ignore parent class methods

    /**
     * Creates an object from every Class given and also adds all of the classes public methods to the stored method map
     *
     * @param c Classes to be added
     * @throws Exception
     */
    public static void addSingletonClassMethods(Class... c) throws Exception {
        for (Class i : c) {
            Constructor constructor = i.getConstructor();
            Object o = constructor.newInstance();
            addObjectAndMethods(o, i);
        }

    }

    /**
     * Loads in the predefined objects and methods from the WebSocketObjectCreator and WebSocketSharedBeans classes
     *
     * @throws Exception
     */
    public static void initialHashMaps() throws Exception {
        clearHashMaps();
        Method[] methodBeans = WebSocketSharedBeans.class.getMethods();
        Method[] methodObjects = WebSocketObjectCreator.class.getMethods();
        for (Method m : methodBeans) {
            if (m.getDeclaringClass() == WebSocketSharedBeans.class) {
                Class c = m.getReturnType();
                Object o = m.invoke(null);
                addObjectAndMethods(o, c);
            }
        }
        for (Method m : methodObjects) {
            if (m.getDeclaringClass() == WebSocketObjectCreator.class) {
                Class c = m.getReturnType();
                Object o = m.invoke(null);
                addObjectAndMethods(o, c);
            }
        }
    }


    private static void clearHashMaps() {
        methodMap = Collections.synchronizedMap(new HashMap<>());
        singletonObjectMap = Collections.synchronizedMap(new HashMap<>());
    }


    /**
     * Adds class methods to to the map
     *
     * @param c classes to be added
     */
    public static void addClassMethods(Class... c) {
        for (Class i : c) {
            Method[] methods = i.getMethods();
            for (Method m : methods) {
                String name = m.toString();

                if ((!singletonObjectMap.containsKey(name)) && m.getDeclaringClass().getPackage() == i.getPackage()) {
                    methodMap.put(m.toString(), m);
                } //If method already exist, it will skip such and an exception won't be throw.
            }
        }
    }

    //Takes in a premade object(such as a singleton java bean)
    //and adds it to the map as well as the methods, Only one instance per class

    /**
     * Adds the given class c's method to the map and create an object from the class
     *
     * @param o object to be created
     * @param c class of the desired object
     * @throws Exception
     */
    public static void addObjectAndMethods(Object o, Class c) throws Exception {
        String name = c.getName();
        if (!singletonObjectMap.containsKey(name)) {
            singletonObjectMap.put(name, o);
        } else {
            throw new Exception("There is already an object of class: " + c.getName());
        }//A generic exception should probably be changed at some point
        addClassMethods(c);
    }


    /**
     * Returns the method map with method names as keys
     *
     * @return method map
     */
    public static Map<String, Method> getMethodMap() {
        return methodMap;
    }

    /**
     * Returns the singleton object map with class names as keys
     *
     * @return singleton object map
     */
    public static Map<String, Object> getSingletonObjectMap() {
        return singletonObjectMap;
    }

    /**
     * returns a method
     *
     * @param methodString the method's full name
     * @return the requested method. Null if not found
     */
    public static Method getMethod(String methodString) {
        return methodMap.get(methodString);
    }

    /**
     * Returns the request shared object
     *
     * @param objectClassName the name of the class of the object
     * @return the requested object
     */
    public static Object getSavedObject(String objectClassName) {
        return singletonObjectMap.get(objectClassName);
    }

    /**
     * returns a set of all valid method names
     *
     * @return set of valid method names
     */
    public static Set<String> getMethods() {
        return methodMap.keySet();
    }

    /**
     * Returns a list of method names associated with the given class name
     *
     * @param className class name of the requested method list
     * @return list of method names
     */
    public static ArrayList<String> getMethodsByClass(String className) {
        ArrayList<String> output = new ArrayList<>();
        Set<String> keys = methodMap.keySet();
        for (String s : keys) {
            if (s.contains(className)) {
                output.add(s);
            }
        }
        return output;
    }

    /**
     * Returns the account session map
     *
     * @return account session map
     */
    public static Map<Account, Session> getAccountSessionMap() {
        return accountSessionMap;
    }

    /**
     * returns session account map
     *
     * @return session account map
     */
    public static Map<Session, Account> getSessionAccountMap() {
        return sessionAccountMap;
    }

    protected static void broadcast(String message) {
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


