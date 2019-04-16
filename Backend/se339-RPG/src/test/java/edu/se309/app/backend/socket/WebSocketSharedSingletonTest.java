//package edu.se309.app.backend.socket;
//
//import edu.se309.app.backend.rest.controller.*;
//import edu.se309.app.backend.rest.entity.Account;
//import edu.se309.app.backend.rest.entity.Building;
//import edu.se309.app.backend.rest.service.StatServiceImplementation;
//import edu.se309.app.backend.rest.service.interfaces.StatService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.lang.reflect.Method;
//import java.util.Map;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//class WebSocketSharedSingletonTest {
//
//    @InjectMocks
//    WebSocketSharedSingleton webSocketSharedSingleton;
//
//    @Mock
//    WebSocketAutoWire webSocketAutoWire;
//
//
//
//    @BeforeEach
//    void afterTest() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        WebSocketSharedSingleton.initialHashMaps();
//    }
//
//    @Test
//    void addClassMethods() {
//        Method[] buildingMethods = Building.class.getMethods();
//        Method[] accountMethods = Account.class.getMethods();
//        WebSocketSharedSingleton.addClassMethods(Building.class, Account.class);
//        Map<String, Method> methods = WebSocketSharedSingleton.getMethodMap();
//        Map<String, Object> objects = WebSocketSharedSingleton.getSingletonObjectMap();
//        assertTrue(objects.isEmpty());
//        for (Method m : buildingMethods) {
//            if (m.getDeclaringClass().getPackage() == Building.class.getPackage()) {
//                assertEquals(m, methods.get(m.toString()));
//            } else {
//                assertNotEquals(m, methods.get(m.toString()));
//            }
//        }
//        for (Method m : accountMethods) {
//            if (m.getDeclaringClass().getPackage() == Account.class.getPackage()) {
//                assertEquals(m, methods.get(m.toString()));
//            } else {
//                assertNotEquals(m, methods.get(m.toString()));
//            }
//        }
//    }
//
//    @Test
//    void addObjectAndMethods() throws Exception {
//        class TestClass {
//            TestClass() {
//            }
//
//            int methodOne() {
//                return 0;
//            }
//
//            void methodTwo() {
//            }
//
//            String methodThree() {
//                return "test";
//            }
//
//        }
//        TestClass testClass = new TestClass();
//        WebSocketSharedSingleton.addObjectAndMethods(testClass, TestClass.class);
//        Map<String, Method> methods = WebSocketSharedSingleton.getMethodMap();
//        Map<String, Object> objects = WebSocketSharedSingleton.getSingletonObjectMap();
//        for (Method m : TestClass.class.getMethods()) {
//            if (m.getDeclaringClass() == TestClass.class) {
//                assertEquals(m, methods.get(m.toString()));
//            } else {
//                assertNotEquals(m, methods.get(m.toString()));
//            }
//        }
//        assertEquals(testClass, objects.get(TestClass.class.getName()));
//    }
//
//    @Test
//    void addSingletonClassMethods() throws Exception {
//        Method[] buildingMethods = Building.class.getMethods();
//        Method[] accountMethods = Account.class.getMethods();
//        WebSocketSharedSingleton.addSingletonClassMethods(Building.class, Account.class);
//        Map<String, Method> methods = WebSocketSharedSingleton.getMethodMap();
//        Map<String, Object> objects = WebSocketSharedSingleton.getSingletonObjectMap();
//        assertEquals(Building.class, objects.get(Building.class.getName()).getClass());
//        assertEquals(Account.class, objects.get(Account.class.getName()).getClass());
//        for (Method m : buildingMethods) {
//            if (m.getDeclaringClass().getPackage() == Building.class.getPackage()) {
//                assertEquals(m, methods.get(m.toString()));
//            } else {
//                assertNotEquals(m, methods.get(m.toString()));
//            }
//        }
//        for (Method m : accountMethods) {
//            if (m.getDeclaringClass().getPackage() == Account.class.getPackage()) {
//                assertEquals(m, methods.get(m.toString()));
//            } else {
//                assertNotEquals(m, methods.get(m.toString()));
//            }
//        }
//    }
//
//    @Test
//    void initialHashMaps() throws Exception {
//
//        when(WebSocketAutoWire.getBean(StatsController.class)).thenReturn(mock(StatsController.class));
//        when(WebSocketAutoWire.getBean(BuildingController.class)).thenReturn(mock(BuildingController.class));
//        when(WebSocketAutoWire.getBean(MonsterAttackController.class)).thenReturn(mock(MonsterAttackController.class));
//        when(WebSocketAutoWire.getBean(MonsterController.class)).thenReturn(mock(MonsterController.class));
//        when(WebSocketAutoWire.getBean(MonsterStatController.class)).thenReturn(mock(MonsterStatController.class));
//
//        System.out.println("Startings");
//        WebSocketSharedSingleton.addSingletonClassMethods(Account.class);
//        WebSocketSharedSingleton.initialHashMaps();
//        assertEquals(null, WebSocketSharedSingleton.getSavedObject(Account.class.getName()));
//        Method[] startMethods = WebSocketSharedObjects.class.getMethods();
//        Map<String, Method> methods = WebSocketSharedSingleton.getMethodMap();
//        Map<String, Object> objects = WebSocketSharedSingleton.getSingletonObjectMap();
//        for (Method m : startMethods) {
//            System.out.println(m.getDeclaringClass());
//            if (m.getDeclaringClass() == WebSocketSharedObjects.class) {
//                assertEquals(m.invoke(null), objects.get(m.getReturnType().getName()));
//                Method[] m2 = m.getReturnType().getMethods();
//                for (Method i : m2) {
//                    if (i.getDeclaringClass().getPackage() == m.getDeclaringClass().getPackage())
//                        System.out.println(i.toString());
//                        assertEquals(i, methods.get(i.toString()));
//                }
//            }
//        }
//    }
//}