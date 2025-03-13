package messageusingtelegram.Usingtelegramapi.Configuration;

import org.springframework.stereotype.Component;

@Component
public class InstanceConfigTelegram {

	
	public String INSTANCE_ID="YOUR_INSTANCE_ID_HERE";

	
	public String CLIENT_ID="YOUR_CLIENT_ID_HERE";


	public String CLIENT_SECRET="YOUR_CLIENT_SECRET_HERE";


	private String TG_GATEWAY_URL="http://api.whatsmate.net/v3/telegram/single/text/message/" + INSTANCE_ID;

	
	
	public InstanceConfigTelegram() {
	}

	public String getINSTANCE_ID() {
		return INSTANCE_ID;
	}

	public String getCLIENT_ID() {
		return CLIENT_ID;
	}

	public String getCLIENT_SECRET() {
		return CLIENT_SECRET;
	}

	public String getTG_GATEWAY_URL() {
		return TG_GATEWAY_URL;
	}

}