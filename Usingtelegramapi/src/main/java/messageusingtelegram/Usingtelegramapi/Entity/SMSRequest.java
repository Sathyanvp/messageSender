package messageusingtelegram.Usingtelegramapi.Entity;

public class SMSRequest {
	private String toPhoneNumber;
    private String message;
    private String fromPhoneNumber;
    
    
	public SMSRequest() {
		
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
