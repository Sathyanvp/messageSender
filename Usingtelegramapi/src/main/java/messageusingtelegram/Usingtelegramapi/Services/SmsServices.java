package messageusingtelegram.Usingtelegramapi.Services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class SmsServices {


	public void sendMessage(String toPhoneNumber, String fromPhoneNumber, String message) {
		// TODO Auto-generated method stub
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		 try {
	            Message message = Message.creator(
	                    new PhoneNumber(toPhoneNumber),
	                    new PhoneNumber(fromPhoneNumber),
	                    messageBody)
	                    .create();

	            System.out.println("SMS sent successfully! SID: " + message.getSid());

	        } catch (com.twilio.exception.ApiException e) {
	            System.err.println("Error sending SMS: " + e.getMessage());
	            e.printStackTrace(); // Print the full stack trace for debugging
	        }

	}

}
