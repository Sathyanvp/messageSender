package messageusingtelegram.Usingtelegramapi.Entity;

import org.springframework.stereotype.Component;


@Component
public class WhatsappMessageRequest {

	private String toPhoneNumber;
	private String fromPhoneNumber;
    private String message;
    
    
    
	public WhatsappMessageRequest() {
    	
	}
    
	public String getToPhoneNumber() {
		return toPhoneNumber;
	}



	public void setToPhoneNumber(String toPhoneNumber) {
		this.toPhoneNumber = toPhoneNumber;
	}



	public String getFromPhoneNumber() {
		return fromPhoneNumber;
	}



	public void setFromPhoneNumber(String fromPhoneNumber) {
		this.fromPhoneNumber = fromPhoneNumber;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}
    
  

  

	
	
}
