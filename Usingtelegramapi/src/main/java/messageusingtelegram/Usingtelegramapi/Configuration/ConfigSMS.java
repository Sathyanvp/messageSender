package messageusingtelegram.Usingtelegramapi.Configuration;

import org.springframework.stereotype.Component;

@Component
public class ConfigSMS {
	
	
	private final String ACCOUNT_SID = "YOUR_ACCOUNT_SID";
	

    private final String AUTH_TOKEN = "YOUR_AUTH_TOKEN";
	
	public String getACCOUNT_SID() {
		return ACCOUNT_SID;
	}
	
	public String getAUTH_TOKEN() {
		return AUTH_TOKEN;
	}
	
}
