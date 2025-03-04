package messageusingtelegram.Usingtelegramapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import messageusingtelegram.Usingtelegramapi.Services.TelegramService;

@RestController
@RequestMapping("/messagesender")
public class Controller {
	private TelegramService telegramservice ;
	
	@PostMapping("/send")
	public void sender(@RequestBody String number ,
			@RequestBody String message, 
			@RequestBody String prefferedChannel){
		
//	    String number = "12025550108";  //  TODO: Specify the recipient's number here. NOT the gateway number
//	    String message = "Howdy, isn't this exciting?";
        
		TelegramService.sendMessage(number, message);
	  }
}
