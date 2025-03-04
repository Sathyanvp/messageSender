package messageusingtelegram.Usingtelegramapi.Services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import messageusingtelegram.Usingtelegramapi.InstanceConfig;

public class TelegramService {
	
	  private InstanceConfig instanceconfig; 
	  public static void sendMessage(String number, String message) throws Exception {
		    // TODO: Should have used a 3rd party library to make a JSON string from an object
		    String jsonPayload = new StringBuilder()
		      .append("{")
		      .append("\"number\":\"")
		      .append(number)
		      .append("\",")
		      .append("\"message\":\"")
		      .append(message)
		      .append("\"")
		      .append("}")
		      .toString();

		    URL url = new URL(instanceconfig.TG_GATEWAY_URL);
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    conn.setDoOutput(true);
		    conn.setRequestMethod("POST");
		    conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
		    conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
		    conn.setRequestProperty("Content-Type", "application/json");

		    OutputStream os = conn.getOutputStream();
		    os.write(jsonPayload.getBytes());
		    os.flush();
		    os.close();

		    int statusCode = conn.getResponseCode();
		    System.out.println("Response from WA Gateway: \n");
		    System.out.println("Status Code: " + statusCode);
		    BufferedReader br = new BufferedReader(new InputStreamReader(
		        (statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()
		      ));
		    String output;
		    while ((output = br.readLine()) != null) {
		        System.out.println(output);
		    }
		    conn.disconnect();
		  }

}
