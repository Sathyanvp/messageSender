# Message Sending Service (Telegram, WhatsApp, SMS)

This Spring Boot application provides a RESTful API for sending messages via Telegram, WhatsApp, and SMS.

## Features

* **Telegram Messaging:** Send text messages to Telegram numbers using the WhatsMate API.
* **WhatsApp Messaging:** Send WhatsApp messages using the Twilio API.
* **SMS Messaging:** Send SMS messages using the Twilio API.
* **Configuration:** Uses configuration classes to manage API keys and credentials.
* **RESTful API:** Provides endpoints for sending messages via each service.
* **Error Handling:** Provides basic error handling and returns appropriate HTTP status codes.
* **Input Validation:** Uses validation constraints to check phone number formats.

## Technologies Used

* Java 17
* Spring Boot
* Twilio API
* WhatsMate API
* Maven

## Prerequisites

* Java Development Kit (JDK) 17 or higher
* Maven
* Twilio Account (for WhatsApp and SMS)
* WhatsMate Account (for Telegram)

## Configuration

1.  **Twilio Configuration:**
    * Create a Twilio account.
    * Obtain your Account SID and Auth Token.
    * Obtain a Twilio phone number (for SMS) or enable the WhatsApp sandbox (for WhatsApp).
    * Update the `WhatsappConfig.java` and `ConfigSMS.java` files with your credentials.
2.  **WhatsMate Configuration:**
    * Create a WhatsMate account.
    * Obtain your Instance ID, Client ID, and Client Secret.
    * Update the `TelegramConfig.java` file with your credentials.

## How to Run

1.  **Clone the Repository:**
    ```bash
    git clone [repository URL]
    cd [repository directory]
    ```
2.  **Update Configuration:**
    * Update the `WhatsappConfig.java`, `ConfigSMS.java`, and `TelegramConfig.java` files with your API credentials.
3.  **Build the Project:**
    ```bash
    mvn clean install
    ```
4.  **Run the Application:**
    ```bash
    mvn spring-boot:run
    ```
5.  **Access the APIs:**
    * The application will run on `http://localhost:8080`.
    * Use tools like Postman or curl to interact with the APIs.

## API Endpoints

### Telegram Messaging

* **Send Telegram Message:**
    * `POST /campaign/messagesender/send/telegram`
    * Request Body: JSON object with `number` and `message`.
    * Example Request Body:
        ```json
        {
          "number": "+1234567890",
          "message": "Hello from Telegram!"
        }
        ```

### WhatsApp Messaging

* **Send WhatsApp Message:**
    * `POST /campaign/messagesender/send/whatsapp`
    * Request Body: JSON object with `toPhoneNumber`, `fromPhoneNumber`, and `message`.
    * Example Request Body:
        ```json
        {
          "toPhoneNumber": "whatsapp:+1234567890",
          "fromPhoneNumber": "whatsapp:+1234567891",
          "message": "Hello from WhatsApp!"
        }
        ```

### SMS Messaging

* **Send SMS Message:**
    * `POST /campaign/messagesender/send/sms`
    * Request Body: JSON object with `toPhoneNumber`, `fromPhoneNumber`, and `message`.
    * Example Request Body:
        ```json
        {
          "toPhoneNumber": "+1234567890",
          "fromPhoneNumber": "+1234567891",
          "message": "Hello from SMS!"
        }
        ```

## Error Handling

* The application returns appropriate HTTP status codes for different scenarios:
    * `200 OK`: Message sent successfully.
    * `500 Internal Server Error`: Error sending message.
    * `400 Bad Request`: Invalid input (e.g., invalid phone number format).

## Future Improvements

* Implement more robust error handling and logging.
* Add message delivery status tracking.
* Add support for sending media messages (images, videos, etc.).
* Add authentication and authorization.
* Add rate limiting.
* Add unit and integration tests.
* Add better input validation.
* Create a more robust configuration management system.

