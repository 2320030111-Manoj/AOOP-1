package Ten;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// Producer class that generates messages
class Producer implements Runnable {
    private BlockingQueue<String> queue;
    private static final String POISON_PILL = "STOP"; // Poison pill message

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int messageCount = 0;
        try {
            // Produce 10 messages
            while (messageCount < 10) {
                String message = "Message-" + messageCount;
                System.out.println("Produced: " + message);
                queue.put(message);  // Put message in the queue (thread-safe)
                messageCount++;
                Thread.sleep(500);  // Simulate time to produce a message (reduce to 500ms)
            }
            // Send poison pill to stop the consumer
            System.out.println("Producer finished producing. Sending poison pill.");
            queue.put(POISON_PILL);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Producer was interrupted.");
        }
    }
}

// Consumer class that consumes messages
class Consumer implements Runnable {
    private BlockingQueue<String> queue;
    private static final String POISON_PILL = "STOP"; // Poison pill message

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = queue.take();  // Take message from the queue (thread-safe)
                
                // Check if the poison pill is received
                if (message.equals(POISON_PILL)) {
                    System.out.println("Consumer received poison pill. Stopping.");
                    break;
                }

                System.out.println("Consumed: " + message);
                Thread.sleep(500);  // Simulate time to consume a message (reduce to 500ms)
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Consumer was interrupted.");
        }
    }
}

// Main application to start Producer and Consumer threads
public class MessagingApp {
    public static void main(String[] args) {
        // Define a shared BlockingQueue with a capacity of 10 messages
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        // Create Producer and Consumer instances
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        // Create and start producer and consumer threads
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        try {
            // Wait for both threads to finish
            producerThread.join();  // Wait for producer to finish
            consumerThread.join();  // Wait for consumer to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Producer and Consumer have stopped.");
    }
}
