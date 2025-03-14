package messageusingtelegram.Usingtelegramapi.Configuration;

import lombok.Data;

@Data
public class WhatsappConfig {
	
	private String authToken = "  ";

	

	
	 
	public String getAuthToken() {
		return authToken;
	}
	 
}
