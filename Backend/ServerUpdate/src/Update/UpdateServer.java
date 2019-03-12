package Update;
import java.time.OffsetDateTime;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class UpdateServer {
	public static void main(String[] args) throws MalformedURLException, IOException {
		OffsetDateTime currentTime = OffsetDateTime.now();
		OffsetDateTime regenTime = currentTime.plusSeconds(10);
		OffsetDateTime respawnTime = currentTime.plusMinutes(1);
		while(true)
		{
			currentTime =OffsetDateTime.now();
			if(currentTime.compareTo(regenTime) >= 0)
			{
				System.out.println("Health Regained");
				regenTime = currentTime.plusSeconds(10);

			}
			if(currentTime.compareTo(respawnTime) >= 0)
			{
				System.out.println("Monsters Respawned");
				respawnTime = currentTime.plusMinutes(1);
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
