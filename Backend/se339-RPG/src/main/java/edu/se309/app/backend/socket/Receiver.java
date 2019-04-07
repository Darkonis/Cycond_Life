//package edu.se309.app.backend.socket;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//
//import java.util.concurrent.CountDownLatch;
//
//
//public class Receiver {
//
//
//    private CountDownLatch latch = new CountDownLatch(1);
//
//    @RabbitHandler
//    public void receive(String in) {
//        System.out.println(" [x] Received '" + in + "'");
//        latch.countDown();
//    }
//
//    public CountDownLatch getLatch() {
//        return latch;
//    }
//}