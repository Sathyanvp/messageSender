package messageusingtelegram.Usingtelegramapi.Configuration;

import lombok.Data;

@Data
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
