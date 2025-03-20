package messageusingtelegram.Usingtelegramapi.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import messageusingtelegram.Usingtelegramapi.Configuration.WhatsappConfig;

@Service
public class WhatsappServices {
	
    private WhatsappConfig whatsappconfig;
   
    
    @Autowired
    public WhatsappServices(WhatsappConfig whatsappconfig) {
		super();
		this.whatsappconfig = whatsappconfig;
		
	}

	

	public  ResponseEntity<String> sendMessage(String toPhoneNumber,String fromPhoneNumber, String whatsappMessage) {
		
        String whatsappAuthToken = whatsappconfig.getwhatsappAuthToken();
        String whatsappAccountSid = whatsappconfig.getWhatsappAccountSid();
        try {
        	 Twilio.init(whatsappAccountSid , whatsappAuthToken);
             Message message = Message.creator(
                     new com.twilio.type.PhoneNumber(fromPhoneNumber),
                     new com.twilio.type.PhoneNumber(toPhoneNumber),
                     whatsappMessage)
                 .create();
            return ResponseEntity.ok("Whatsapp message sent successfully."+message.getSid());
            
        } 
        catch (Exception e) {
        	 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request");
        }
		
	}

}
