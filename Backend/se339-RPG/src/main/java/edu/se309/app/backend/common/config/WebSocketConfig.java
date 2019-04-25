package edu.se309.app.backend.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Configuration class for the websocket
 */
@Configuration
public class WebSocketConfig {

    /**
     * Returns a new ServiceEndpointExporter
     *
     * @return a new ServiceEndPointExporter
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
