package messageusingtelegram.Usingtelegramapi;
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
