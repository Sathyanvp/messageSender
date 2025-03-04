package messageusingtelegram.Usingtelegramapi;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.TelegramBotsApi;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
//import jakarta.annotation.PostConstruct;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/telegram")
//public class TelegramController {
//
//    @Value("${telegram.bot.token}")
//    private String botToken;
//
//    @Value("${telegram.chat.id}")
//    private String chatId;
//
//    private TelegramLongPollingBot bot;
//
//    @PostConstruct
//    public void init() {
//        try {
//            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//            bot = new TelegramLongPollingBot(botToken) {
//                @Override
//                public void onUpdateReceived(Update update) {
//                    // We don't need to process updates in this case
//                }
//
//                @Override
//                public String getBotUsername() {
//                    return "YourBotUsername"; // Replace with your bot's username
//                }
//            };
//            botsApi.registerBot(bot);
//        } catch (TelegramApiException e) {
//            e.printStackTrace(); // Handle exception properly in a production environment.
//        }
//    }
//
//    @PostMapping("/send")
//    public ResponseEntity<String> sendTelegramMessage(@RequestBody Map<String, String> payload) {
//        String messageBody = payload.get("message");
//
//        if (messageBody == null) {
//            return ResponseEntity.badRequest().body("Missing 'message' field.");
//        }
//
//        try {
//            SendMessage message = new SendMessage();
//            message.setChatId(chatId);
//            message.setText(messageBody);
//            message.enableMarkdown(true); // Enable Markdown formatting
//            bot.execute(message);
//
//            return ResponseEntity.ok("Message sent successfully.");
//
//        } catch (TelegramApiException e) {
//            e.printStackTrace(); // Log the exception properly in a production environment
//            return ResponseEntity.internalServerError().body("Failed to send message: " + e.getMessage());
//        }
//    }
//}
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//
//@RestController
//@RequestMapping("/telegram")
//public class TelegramController {
//
//
//	private JdbcTemplate jdbcTemplate;
//
//   
//   private RestTemplate restTemplate;
//
//    public TelegramController(JdbcTemplate jdbcTemplate) {
//		super();
//		this.jdbcTemplate = jdbcTemplate;
//		
//	}
//
//
//    @Value("${telegram.bot.token}")
//    private String telegramBotToken;
//
//    @Value("${telegram.chat.id}")
//    private String fallbackChatId; // Used if customer specific telegram number is not found.
//
//    @PostMapping("/send")
//    public ResponseEntity<String> sendTelegramMessage(
//            @RequestParam int customerId,
//            @RequestParam String message) {
//
//        try {
//            String telegramNumberQuery = "SELECT telegram_number FROM customer WHERE id = ?";
//            String telegramNumber = jdbcTemplate.queryForObject(telegramNumberQuery, String.class, customerId);
//
//            if (telegramNumber == null || telegramNumber.isEmpty()) {
//                System.out.println("Customer telegram number not found. Using fallback chat ID.");
//                telegramNumber = fallbackChatId; // Use fallback if customer number not found
//            }
//
//            sendTelegram(telegramNumber, message);
//
//            return ResponseEntity.ok("Telegram message sent successfully.");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.internalServerError().body("Error sending Telegram message: " + e.getMessage());
//        }
//    }
//
//    private void sendTelegram(String telegramNumber, String message) {
//        try {
//            String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
//            String url = String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
//                    telegramBotToken, telegramNumber, encodedMessage);
//
//            restTemplate.getForEntity(url, String.class);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}



import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class InstanceConfig {



    
    @Value("${instance.id}")
    public static  String INSTANCE_ID ;
    
    @Value("${client.id}")
    public static  String CLIENT_ID;
    
    @Value("${client.secret}")
    public static String CLIENT_SECRET;
    @Value("$")
    private static final String TG_GATEWAY_URL ;


  public static void main(String[] args) throws Exception {
    String number = "12025550108";  //  TODO: Specify the recipient's number here. NOT the gateway number
    String message = "Howdy, isn't this exciting?";

    TelegramSender.sendMessage(number, message);
  }

  /**
   * Sends out a WhatsApp message via WhatsMate WA Gateway.
   */
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

    URL url = new URL(TG_GATEWAY_URL);
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