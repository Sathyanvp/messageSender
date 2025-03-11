package messageusingtelegram.Usingtelegramapi.Configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class InstanceConfigTelegram {

	@Value("${telegram.instance.id}")
	public String INSTANCE_ID;

	@Value("${telegram.client.id}")
	public String CLIENT_ID;

	@Value("${telegram.client.secret}")
	public String CLIENT_SECRET;

	@Value("${TG.GATEWAY.URL}")
	private String TG_GATEWAY_URL;

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