package messageusingtelegram.Usingtelegramapi.Entity;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Component
public class TelegramMessageRequest {
	@NotBlank(message = "Please enter recipent number")
	@Pattern(regexp = "^\\+?\\d{1,14}$", message = "Invalid  phone number format")
    private String number;
	@NotBlank(message = "Please enter message to be sent")
    private String message;


    public  TelegramMessageRequest () {}
    
    public void setNumber(String number) {
        this.number = number;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getNumber() {
        return number;
    }
    
    public String getMessage() {
        return message;
    }
    
  

}

