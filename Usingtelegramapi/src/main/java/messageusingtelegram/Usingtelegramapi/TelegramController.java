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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/telegram")
public class TelegramController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${telegram.bot.token}")
    private String telegramBotToken;

    @Value("${telegram.chat.id}")
    private String fallbackChatId; // Used if customer specific telegram number is not found.

    @PostMapping("/send")
    public ResponseEntity<String> sendTelegramMessage(
            @RequestParam int customerId,
            @RequestParam String message) {

        try {
            String telegramNumberQuery = "SELECT telegram_number FROM customer WHERE id = ?";
            String telegramNumber = jdbcTemplate.queryForObject(telegramNumberQuery, String.class, customerId);

            if (telegramNumber == null || telegramNumber.isEmpty()) {
                System.out.println("Customer telegram number not found. Using fallback chat ID.");
                telegramNumber = fallbackChatId; // Use fallback if customer number not found
            }

            sendTelegram(telegramNumber, message);

            return ResponseEntity.ok("Telegram message sent successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error sending Telegram message: " + e.getMessage());
        }
    }

    private void sendTelegram(String telegramNumber, String message) {
        try {
            String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
            String url = String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
                    telegramBotToken, telegramNumber, encodedMessage);

            restTemplate.getForEntity(url, String.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}