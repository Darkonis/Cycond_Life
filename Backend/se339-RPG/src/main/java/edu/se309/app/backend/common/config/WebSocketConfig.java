<<<<<<< HEAD:Backend/se339-RPG/src/main/java/edu/se309/app/backend/socket/WebSocketConfig.java
package edu.se309.app.backend.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
=======
package edu.se309.app.backend.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
>>>>>>> 40fa12516b2197fdfb086e38e7b2fbb18d1ebc33:Backend/se339-RPG/src/main/java/edu/se309/app/backend/common/config/WebSocketConfig.java
