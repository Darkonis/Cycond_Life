package edu.se309.app.backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.OffsetDateTime;


/**
 * Main spring boot class used to start the server
 */
@SpringBootApplication
@EnableScheduling
public class Se309RpgApplication {

    /**
     * Starts the server
     *
     * @param args args for the server
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Se309RpgApplication.class, args);
        OffsetDateTime currentTime = OffsetDateTime.now();
        OffsetDateTime regenTime = currentTime.plusSeconds(10);
        OffsetDateTime respawnTime = currentTime.plusMinutes(15);
        while (true) {
            currentTime = OffsetDateTime.now();
            if (currentTime.compareTo(regenTime) >= 0) {
                regenTime = currentTime.plusSeconds(10);
            }
            if (currentTime.compareTo(respawnTime) >= 0) {
                respawnTime = currentTime.plusMinutes(15);
                String url = "http://cs309-sd-6.misc.iastate.edu:8080/api/monsters/generate";
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.flush();
                wr.close();
                con.getResponseCode();
            }
        }
    }


}


