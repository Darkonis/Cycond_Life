package edu.se309.app.backend;


import edu.se309.app.backend.socket.WebSocketConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@EnableConfigurationProperties(WebSocketConfig.class)
public class Se309RpgApplicationTests {

    @Test
    public void contextLoads() {
    }
}
