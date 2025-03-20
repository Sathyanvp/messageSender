package messageusingtelegram.Usingtelegramapi.Configuration;

import org.springframework.stereotype.Component;


@Component
public class WhatsappConfig {
	
	private String whatsappAuthToken = "  ";
    private String whatsappAccountSid = "";
	

	
	 
	public String getWhatsappAccountSid() {
		return whatsappAccountSid;
	}

	public String getwhatsappAuthToken() {
		return whatsappAuthToken;
	}
	 
}
