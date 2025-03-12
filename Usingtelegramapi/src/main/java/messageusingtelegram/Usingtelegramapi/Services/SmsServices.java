package messageusingtelegram.Usingtelegramapi.Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import messageusingtelegram.Usingtelegramapi.Configuration.ConfigSMS;

public class SmsServices {

	private ConfigSMS configsms;
	
	public void sendMessage(String toPhoneNumber, String fromPhoneNumber, String text) {
		// TODO Auto-generated method stub
		Twilio.init(configsms.getACCOUNT_SID(), configsms.getAUTH_TOKEN());
		 try {
	            Message message = Message.creator(
	                    new PhoneNumber(toPhoneNumber),
	                    new PhoneNumber(fromPhoneNumber),
	                   text)
	                    .create();

	            System.out.println("SMS sent successfully! SID: " + message.getSid());

	        } catch (com.twilio.exception.ApiException e) {
	            System.err.println("Error sending SMS: " + e.getMessage());
	            e.printStackTrace(); // Print the full stack trace for debugging
	        }

	}

}
