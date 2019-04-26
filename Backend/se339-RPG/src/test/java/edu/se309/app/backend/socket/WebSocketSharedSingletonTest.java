package edu.se309.app.backend.socket;

import edu.se309.app.backend.rest.entity.Account;
import edu.se309.app.backend.rest.entity.Building;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebSocketSharedSingletonTest {


    @BeforeEach
    void afterTest() throws Exception {
        WebSocketSharedSingleton.initialHashMaps();
    }

    @Test
    void addClassMethods() {
        Method[] buildingMethods = Building.class.getMethods();
        Method[] accountMethods = Account.class.getMethods();
        WebSocketSharedSingleton.addClassMethods(Building.class, Account.class);
        Map<String, Method> methods = WebSocketSharedSingleton.getMethodMap();
        Map<String, Object> objects = WebSocketSharedSingleton.getSingletonObjectMap();
        for (Method m : buildingMethods) {
            if (m.getDeclaringClass().getPackage() == Building.class.getPackage()) {
                System.out.println(m.toString());//TODO REMOVE PRINT
                assertEquals(m, methods.get(m.toString()));
            } else {
                assertNotEquals(m, methods.get(m.toString()));
            }
        }
        for (Method m : accountMethods) {
            if (m.getDeclaringClass().getPackage() == Account.class.getPackage()) {
                assertEquals(m, methods.get(m.toString()));
            } else {
                assertNotEquals(m, methods.get(m.toString()));
            }
        }
    }

    @Test
    void addObjectAndMethods() throws Exception {
        class TestClass {
            TestClass() {
            }

            int methodOne() {
                return 0;
            }

            void methodTwo() {
            }

            String methodThree() {
                return "test";
            }

        }
        TestClass testClass = new TestClass();
        WebSocketSharedSingleton.addObjectAndMethods(testClass, TestClass.class);
        Map<String, Method> methods = WebSocketSharedSingleton.getMethodMap();
        Map<String, Object> objects = WebSocketSharedSingleton.getSingletonObjectMap();
        for (Method m : TestClass.class.getMethods()) {
            if (m.getDeclaringClass() == TestClass.class) {
                assertEquals(m, methods.get(m.toString()));
            } else {
                assertNotEquals(m, methods.get(m.toString()));
            }
        }
        assertEquals(testClass, objects.get(TestClass.class.getName()));
    }

    @Test
    void addSingletonClassMethods() throws Exception {
        Method[] buildingMethods = Building.class.getMethods();
        Method[] accountMethods = Account.class.getMethods();
        WebSocketSharedSingleton.addSingletonClassMethods(Building.class, Account.class);
        Map<String, Method> methods = WebSocketSharedSingleton.getMethodMap();
        Map<String, Object> objects = WebSocketSharedSingleton.getSingletonObjectMap();
        assertEquals(Building.class, objects.get(Building.class.getName()).getClass());
        assertEquals(Account.class, objects.get(Account.class.getName()).getClass());
        for (Method m : buildingMethods) {
            if (m.getDeclaringClass().getPackage() == Building.class.getPackage()) {
                assertEquals(m, methods.get(m.toString()));
            } else {
                assertNotEquals(m, methods.get(m.toString()));
            }
        }
        for (Method m : accountMethods) {
            if (m.getDeclaringClass().getPackage() == Account.class.getPackage()) {
                assertEquals(m, methods.get(m.toString()));
            } else {
                assertNotEquals(m, methods.get(m.toString()));
            }
        }
    }

    @Test
    void initialHashMaps() throws Exception {
        System.out.println("Startings");
        WebSocketSharedSingleton.addSingletonClassMethods(Account.class);
        WebSocketSharedSingleton.initialHashMaps();
        assertNull(WebSocketSharedSingleton.getSavedObject(Account.class.getName()));
        Method[] startMethods = WebSocketSharedBeans.class.getMethods();
        Map<String, Method> methods = WebSocketSharedSingleton.getMethodMap();
        Map<String, Object> objects = WebSocketSharedSingleton.getSingletonObjectMap();
        for (Method m : startMethods) {
            if (m.getDeclaringClass() == WebSocketSharedBeans.class) {
                assertEquals(m.invoke(null), objects.get(m.getReturnType().getName()));
                Method[] m2 = m.getReturnType().getMethods();
                for (Method i : m2) {
                    if (i.getDeclaringClass().getPackage() == m.getDeclaringClass().getPackage()) {
                        assertEquals(i, methods.get(i.toString()));
                    }
                }
            }
        }
    }
}