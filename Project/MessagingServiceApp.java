package Project;

//Base Interface
interface MessagingService {
 void sendMessage(String message);
}

//Concrete Class (BasicMessagingService)
class BasicMessagingService implements MessagingService {
 @Override
 public void sendMessage(String message) {
     System.out.println("Sending message: " + message);
 }
}

//Decorator Class
abstract class MessageDecorator implements MessagingService {
 protected MessagingService wrappedService;

 public MessageDecorator(MessagingService service) {
     this.wrappedService = service;
 }

 @Override
 public void sendMessage(String message) {
     wrappedService.sendMessage(message);  // Delegate to the wrapped service
 }
}

//Encryption Decorator
class EncryptionDecorator extends MessageDecorator {
 public EncryptionDecorator(MessagingService service) {
     super(service);
 }

 @Override
 public void sendMessage(String message) {
     String encryptedMessage = encryptMessage(message);
     super.sendMessage(encryptedMessage);
 }

 private String encryptMessage(String message) {
     return new StringBuilder(message).reverse().toString(); // Simple reverse encryption
 }
}

//Compression Decorator
class CompressionDecorator extends MessageDecorator {
 public CompressionDecorator(MessagingService service) {
     super(service);
 }

 @Override
 public void sendMessage(String message) {
     String compressedMessage = compressMessage(message);
     super.sendMessage(compressedMessage);
 }

 private String compressMessage(String message) {
     return message.replaceAll(" ", ""); // Remove spaces as a simple compression
 }
}


public class MessagingServiceApp {
 public static void main(String[] args) {
     MessagingService basicService = new BasicMessagingService();

     System.out.println("=== Basic Service ===");
     basicService.sendMessage("Hello World");

     System.out.println("\n=== Encrypted Service ===");
     MessagingService encryptedService = new EncryptionDecorator(basicService);
     encryptedService.sendMessage("Hello World");

     System.out.println("\n=== Compressed and Encrypted Service ===");
     MessagingService compressedEncryptedService = new CompressionDecorator(encryptedService);
     compressedEncryptedService.sendMessage("Hello World");
 }
}

