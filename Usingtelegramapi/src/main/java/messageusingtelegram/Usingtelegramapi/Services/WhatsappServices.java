package messageusingtelegram.Usingtelegramapi.Services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import messageusingtelegram.Usingtelegramapi.Configuration.WhatsappConfig;
import messageusingtelegram.Usingtelegramapi.Entity.WhatsappMessageRequest;

public class WhatsappServices {
	
    private WhatsappConfig whatsappconfig;
    private WhatsappMessageRequest whatsappmessagerequest;
    
    @Autowired
    public WhatsappServices(WhatsappConfig whatsappconfig, WhatsappMessageRequest whatsappmessagerequest) {
		super();
		this.whatsappconfig = whatsappconfig;
		this.whatsappmessagerequest = whatsappmessagerequest;
	}

	

	public  ResponseEntity<String> sendMessage(String toPhoneNumber,String fromPhoneNumber, String whatsappMessage) {
		
        String authToken = whatsappconfig.getAuthToken();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://graph.facebook.com/v13.0/"+fromPhoneNumber+"/messages"))
                .header("Authorization", "Bearer "+authToken)
                .header("Content-Type", "application/json")
                // Getting started example
                //.POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \""+phoneNumber+"\", \"type\": \"template\", \"template\": { \"name\": \"hello_world\", \"language\": { \"code\": \"en_US\" } } }"))
                // Text message example
                .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \""+toPhoneNumber+"\", \"type\": \"text\", \"text\": { \"preview_url\": false, \"body\": "+whatsappMessage+ "} }"))
                // Bespoke message template example
//                .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \""+toPhoneNumber+"\", \"type\": \"template\", \"template\": { \"name\": \"new_customer_offer\", \"language\": { \"code\": \"en\" }, \"components\":[{\"type\":\"header\",\"parameters\":[{\"type\":\"image\",\"image\":{\"link\":\"https://i.ibb.co/DRMHqRj/welcome.jpg\"}}]},{\"type\":\"body\",\"parameters\":[{\"type\":\"text\",\"text\":\"John Smith\"}]}] } }"))
                .build();
            HttpClient http = HttpClient.newHttpClient();
            HttpResponse<String> response = http.send(request,BodyHandlers.ofString());
            ResponseEntity<String> responseEntity = ResponseEntity
                    .status(response.statusCode())
                    .headers(httpHeaders -> response.headers().map().forEach(httpHeaders::addAll))
                    .body(response.body());
            return responseEntity;
            
        } catch (URISyntaxException | IOException | InterruptedException e) {
        	 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request");
        }
		
	}

}
