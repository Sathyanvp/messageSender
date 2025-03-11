package messageusingtelegram.Usingtelegramapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import messageusingtelegram.Usingtelegramapi.Entity.SMSRequest;
import messageusingtelegram.Usingtelegramapi.Entity.TelegramMessageRequest;
import messageusingtelegram.Usingtelegramapi.Entity.WhatsappMessageRequest;
import messageusingtelegram.Usingtelegramapi.Services.SmsServices;
import messageusingtelegram.Usingtelegramapi.Services.TelegramService;
import messageusingtelegram.Usingtelegramapi.Services.WhatsappServices;



@RestController
@RequestMapping("/messagesender")
public class Controller {

    private final TelegramService telegramservice;
    private final WhatsappServices whatsappservice;
    private final SmsServices smsservice;

    @Autowired
    public Controller(TelegramService telegramservice, WhatsappServices whatsappservice, SmsServices smsservice) {
        this.telegramservice = telegramservice;
        this.whatsappservice = whatsappservice;
        this.smsservice = smsservice;
    }

    @PostMapping("/send/telegram")
    public ResponseEntity<String> sender(@RequestBody TelegramMessageRequest telegramrequest) {

        String number = telegramrequest.getNumber();
        String message = telegramrequest.getMessage();

        
                try {
                    telegramservice.sendMessage(number, message);
                    return ResponseEntity.ok("Telegram message sent successfully.");
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.internalServerError().body("Error sending message: " + e.getMessage());
                }
//            case "whatsapp":
//                try {
//                    whatsappservice.sendMessage(number, message);
//                    return ResponseEntity.ok("WhatsApp message sent successfully.");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    return ResponseEntity.internalServerError().body("Error sending message: " + e.getMessage());
//                }
//            case "sms":
//                try {
//                    smsservice.sendMessage(number, message);
//                    return ResponseEntity.ok("SMS message sent successfully.");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    return ResponseEntity.internalServerError().body("Error sending message: " + e.getMessage());
//                }
//            default:
//                return ResponseEntity.badRequest().body("Invalid preferred channel.");
        }
    @PostMapping("/send/whatsapp")
    public ResponseEntity<String> sender(@RequestBody SMSRequest smsrequest) {
    	 String toPhoneNumber = smsrequest.getToPhoneNumber();
         String message = smsrequest.getMessage();
         String fromPhoneNumber  = smsrequest.getFromPhoneNumber();
    	try {
    		smsservice.sendMessage(toPhoneNumber,fromPhoneNumber,message);
            return ResponseEntity.ok("SMS message sent successfully.");
     } 
        catch (Exception e) {
            e.printStackTrace();
           return ResponseEntity.internalServerError().body("Error sending message: " + e.getMessage());
    }
    }
}