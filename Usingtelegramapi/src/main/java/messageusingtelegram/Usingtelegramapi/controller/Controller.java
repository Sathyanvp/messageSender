package messageusingtelegram.Usingtelegramapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import messageusingtelegram.Usingtelegramapi.Services.SmsServices;
import messageusingtelegram.Usingtelegramapi.Services.TelegramService;
import messageusingtelegram.Usingtelegramapi.Services.WhatsappServices;

@RestController
@RequestMapping("/messagesender")
public class Controller {

	private TelegramService telegramservice ;
	private WhatsappServices whatsappservice;
	private SmsServices smsservice;
	@Autowired
	public Controller(TelegramService telegramservice, WhatsappServices whatsappservice, SmsServices smsservice) {
		super();
		this.telegramservice = telegramservice;
		this.whatsappservice = whatsappservice;
		this.smsservice = smsservice;
	}

	
	
	
	@PostMapping("/send")
	public ResponseEntity<String> sender(@RequestBody String number ,
			@RequestBody String message, 
			@RequestBody String whatsappservice){
		
//	    String number = "12025550108";  //  TODO: Specify the recipient's number here. NOT the gateway number
//	    String message = "Howdy, isn't this exciting?";
        switch (prefferedChannel.toLowerCase()) {
        case "telegram":
        	 try {
                 telegramservice.sendMessage(number, message); // Call on the instance
                 return ResponseEntity.ok("Message sent successfully.");
             } catch (Exception e) {
                 e.printStackTrace();
                 return ResponseEntity.internalServerError().body("Error sending message: " + e.getMessage());
             }
        	
        case "whatsapp":
        	 try {
        		 whatsappservice.sendMessage(number, message); // Call on the instance
                 return ResponseEntity.ok("Message sent successfully.");
             } catch (Exception e) {
                 e.printStackTrace();
                 return ResponseEntity.internalServerError().body("Error sending message: " + e.getMessage());
             }
        case "sms":
        	 try {
        		 smsservice.sendMessage(number, message); // Call on the instance
                 return ResponseEntity.ok("Message sent successfully.");
             } catch (Exception e) {
                 e.printStackTrace();
                 return ResponseEntity.internalServerError().body("Error sending message: " + e.getMessage());
             }
        }
		
	  }
}
