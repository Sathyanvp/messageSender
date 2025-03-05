
package messageusingtelegram.Usingtelegramapi.Services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import messageusingtelegram.Usingtelegramapi.InstanceConfig;

@Service
public class TelegramService {

    private InstanceConfig instanceconfig;

    @Autowired
    public TelegramService(InstanceConfig instanceconfig) {
        this.instanceconfig = instanceconfig;
    }

    public  void sendMessage(String number, String message) throws Exception { // added throws Exception

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

        URL url = new URL(instanceconfig.getTG_GATEWAY_URL());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("X-WM-CLIENT-ID", instanceconfig.getCLIENT_ID()); // Correct access
        conn.setRequestProperty("X-WM-CLIENT-SECRET", instanceconfig.getCLIENT_SECRET()); // Correct access
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
