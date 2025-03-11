package messageusingtelegram.Usingtelegramapi.Configuration;

import org.springframework.beans.factory.annotation.Value;

public class ConfigSMS {
	@Value("${sms.account.sid}")
	public static final String ACCOUNT_SID = "YOUR_ACCOUNT_SID";
	@Value("${sms.auth.token}")
    public static final String AUTH_TOKEN = "YOUR_AUTH_TOKEN";
}
