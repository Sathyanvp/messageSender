package messageusingtelegram.Usingtelegramapi.Entity;


public class TelegramMessageRequest {
    private String number;
    private String message;
    private String preferredChannel;

    public  TelegramMessageRequest () {}
    
    public void setNumber(String number) {
        this.number = number;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setPreferredChannel(String preferredChannel) {
        this.preferredChannel = preferredChannel;
    }
    
    public String getNumber() {
        return number;
    }
    
    public String getMessage() {
        return message;
    }
    
    public String getPreferredChannel() {
        return preferredChannel;
    }
    

}

