package edu.se309.app.backend.socket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

@Component
public class WebSocketSharedSingleton {

    private static Map<String, Method> methodMap = Collections.synchronizedMap(new HashMap<>());
    private static Map<String, Object> singletonObjectMap = Collections.synchronizedMap(new HashMap<>());
    private static WebSocketSharedSingleton sharedSingleton;


    private WebSocketSharedSingleton() throws Exception {
        initialHashMaps();

    }

    @Autowired
    public static WebSocketSharedSingleton webSocketSharedSingleton(){
        if (sharedSingleton == null){
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
    public static void addSingletonClassMethods(Class...c) throws Exception{
       for(Class i:c){
           Constructor constructor = i.getConstructor();
           Object o = constructor.newInstance();
           addObjectAndMethods(o,i);
       }

    }

    public static void initialHashMaps() throws Exception{
        clearHashMaps();
        Method[] methods = WebSocketSharedObjects.class.getMethods();
        for (Method m: methods){
            if(m.getDeclaringClass() == WebSocketSharedObjects.class) {
                Class c = m.getReturnType();
                Object o = m.invoke(null);
                addObjectAndMethods(o, c);
            }
        }
    }


    private static void clearHashMaps(){
        methodMap = Collections.synchronizedMap(new HashMap<>());
        singletonObjectMap = Collections.synchronizedMap(new HashMap<>());
    }


    //Add methods to the methodMap
    public static void addClassMethods(Class...c){
        for(Class i:c) {
            Method[] methods = i.getMethods();
            for (Method m : methods) {
                String name = m.toString();

                if ((!singletonObjectMap.containsKey(name)) && m.getDeclaringClass().getPackage() == i.getPackage()) {
                    System.out.println();
                    methodMap.put(m.toString(), m);
                } //If method already exist, it will skip such and an exception won't be throw.
            }
        }
    }

    //Takes in a premade object(such as a singleton java bean)
    //and adds it to the map as well as the methods, Only one instance per class
    public static void addObjectAndMethods(Object o, Class c) throws Exception{
        String name = c.getName();
        if (!singletonObjectMap.containsKey(name)) {
            singletonObjectMap.put(name, o);
        } else{
            throw new Exception("There is only an object of class: " + c.getName());
        }//A generic exception should probably be changed at some point
        addClassMethods(c);
    }



    public static Map<String, Method> getMethodMap() {
        return methodMap;
    }

    public static Map<String, Object> getSingletonObjectMap() {
        return singletonObjectMap;
    }

    public static Method getMethod(String methodString){
        return methodMap.get(methodString);
    }

    public static Object getSavedObject(String objectClassName){
        return singletonObjectMap.get(objectClassName);
    }

    public static Set<String> getMethods(){
        return methodMap.keySet();
    }

    public static ArrayList<String> getMethodsByClass(String className){
        ArrayList<String> output = new ArrayList<>();
        Set<String> keys = methodMap.keySet();
        for (String s: keys){
            if (s.contains(className)){
                output.add(s);
            }
        }
        return output;
    }

}

