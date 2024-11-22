package CO_1;

//Step 1: Define the MessagingService interface
interface MessagingService {
 String sendMessage(String message);
 String receiveMessage(String message);
}

//Step 2: Create the BasicMessagingService
class BasicMessagingService implements MessagingService {
 @Override
 public String sendMessage(String message) {
     System.out.println("Sending: " + message);
     return message;
 }

 @Override
 public String receiveMessage(String message) {
     System.out.println("Receiving: " + message);
     return message;
 }
}

//Step 3: Create the abstract MessageDecorator
abstract class MessageDecorator implements MessagingService {
 protected MessagingService messagingService;

 public MessageDecorator(MessagingService messagingService) {
     this.messagingService = messagingService;
 }

 @Override
 public String sendMessage(String message) {
     return messagingService.sendMessage(message);
 }

 @Override
 public String receiveMessage(String message) {
     return messagingService.receiveMessage(message);
 }
}

//Step 4: Implement concrete decorators

//Encryption Decorator
class EncryptionDecorator extends MessageDecorator {
 public EncryptionDecorator(MessagingService messagingService) {
     super(messagingService);
 }

 @Override
 public String sendMessage(String message) {
     message = encrypt(message);
     System.out.println("Encrypting and Sending: " + message);
     return super.sendMessage(message);
 }

 @Override
 public String receiveMessage(String message) {
     message = super.receiveMessage(message);
     return decrypt(message);
 }

 private String encrypt(String message) {
     return new StringBuilder(message).reverse().toString(); // Simple reverse for encryption
 }

 private String decrypt(String message) {
     return new StringBuilder(message).reverse().toString(); // Reverse back for decryption
 }
}

//Compression Decorator
class CompressionDecorator extends MessageDecorator {
 public CompressionDecorator(MessagingService messagingService) {
     super(messagingService);
 }

 @Override
 public String sendMessage(String message) {
     message = compress(message);
     System.out.println("Compressing and Sending: " + message);
     return super.sendMessage(message);
 }

 @Override
 public String receiveMessage(String message) {
     message = super.receiveMessage(message);
     return decompress(message);
 }

 private String compress(String message) {
     return message.replaceAll(" ", ""); // Simple compression by removing spaces
 }

 private String decompress(String message) {
     return message; // No actual decompression logic for simplicity
 }
}

//Step 5: Test the implementation
public class Main {
 public static void main(String[] args) {
     MessagingService basicService = new BasicMessagingService();

     // Add Encryption
     MessagingService encryptedService = new EncryptionDecorator(basicService);

     // Add Compression on top of Encryption
     MessagingService encryptedAndCompressedService = new CompressionDecorator(encryptedService);

     String message = "Hello World";

     // Send and Receive with all features
     String sentMessage = encryptedAndCompressedService.sendMessage(message);
     String receivedMessage = encryptedAndCompressedService.receiveMessage(sentMessage);

     System.out.println("Final Received Message: " + receivedMessage);
 }
}

