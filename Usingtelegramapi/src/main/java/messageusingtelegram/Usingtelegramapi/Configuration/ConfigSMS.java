package messageusingtelegram.Usingtelegramapi.Configuration;

import org.springframework.beans.factory.annotation.Value;

public class ConfigSMS {
	
	@Value("${sms.account.sid}")
	private final String ACCOUNT_SID = "YOUR_ACCOUNT_SID";
	
	@Value("${sms.auth.token}")
    private final String AUTH_TOKEN = "YOUR_AUTH_TOKEN";
	
	public String getACCOUNT_SID() {
		return ACCOUNT_SID;
	}
	
	public String getAUTH_TOKEN() {
		return AUTH_TOKEN;
	}
	
}
