package edu.se309.app.backend.common.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.se309.app.backend.service.ChatService;

@Configuration
@EnableRabbit
public class RabbitConfig  {
	
	@Bean
    public SimpleRabbitListenerContainerFactory myFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer) {
	       SimpleRabbitListenerContainerFactory factory = 
	    		   new SimpleRabbitListenerContainerFactory();
	       configurer.configure(factory, connectionFactory);
	       factory.setMessageConverter(myMessageConverter());
	       return factory;
	     }
	


}
