package edu.se309.app.backend.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
	
	private static Logger logger = LogManager.getLogger(ChatService.class);
	
	 @RabbitListener(containerFactory="myFactory", queues="myQueue")
     public void process(String msg) {
         logger.warn("Chat Service");
     }
 }


