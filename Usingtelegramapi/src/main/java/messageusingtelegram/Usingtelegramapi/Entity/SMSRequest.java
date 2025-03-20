package messageusingtelegram.Usingtelegramapi.Entity;

import org.springframework.stereotype.Component;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Component
public class SMSRequest {
	
	@NotBlank(message = "Please enter recipent number")
	@Pattern(regexp = "^\\+?\\d{1,14}$", message = "Invalid 'to' phone number format")
	private String toPhoneNumber;
	@NotBlank(message = "Please enter the meassage to be sent")
    private String message;
	@NotBlank(message = "Please enter your number")
	@Pattern(regexp = "^\\+?\\d{1,14}$", message = "Invalid  'from' phone number format")
    private String fromPhoneNumber;
    
    
	public SMSRequest() {
		//default constructor
	}
	
	public String getToPhoneNumber() {
		return toPhoneNumber;
	}
	
	public void setToPhoneNumber(String toPhoneNumber) {
		this.toPhoneNumber = toPhoneNumber;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getFromPhoneNumber() {
		return fromPhoneNumber;
	}
	
	public void setFromPhoneNumber(String fromPhoneNumber) {
		this.fromPhoneNumber = fromPhoneNumber;
	}

}
