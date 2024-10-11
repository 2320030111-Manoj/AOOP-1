package Ten;

import java.util.LinkedList;
import java.util.Queue;

// Bounded buffer class
class BoundedBuffer {
    private final Queue<Integer> buffer;
    private final int capacity;

    // Constructor
    public BoundedBuffer(int capacity) {
        this.buffer = new LinkedList<>();
        this.capacity = capacity;
    }

    // Method for the producer to add items to the buffer
    public synchronized void produce(int item) throws InterruptedException {
        // Wait if the buffer is full
        while (buffer.size() == capacity) {
            System.out.println("Buffer is full. Producer is waiting.");
            wait();  // Wait until there is space in the buffer
        }

        // Add item to the buffer
        buffer.add(item);
        System.out.println("Produced: " + item);

        // Notify the consumer that an item is available
        notify();
    }

    // Method for the consumer to remove items from the buffer
    public synchronized int consume() throws InterruptedException {
        // Wait if the buffer is empty
        while (buffer.isEmpty()) {
            System.out.println("Buffer is empty. Consumer is waiting.");
            wait();  // Wait until there is something in the buffer
        }

        // Remove item from the buffer
        int item = buffer.poll();
        System.out.println("Consumed: " + item);

        // Notify the producer that there is space available
        notify();

        return item;
    }
}

// Producer class that adds items to the buffer
class Producer1 implements Runnable {
    private final BoundedBuffer buffer;

    public Producer1(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int item = 0;
        try {
            while (item < 20) {  // Produce 20 items
                buffer.produce(item);
                item++;
                Thread.sleep(500);  // Simulate time taken to produce an item
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Consumer class that consumes items from the buffer
class Consumer1 implements Runnable {
    private final BoundedBuffer buffer;

    public Consumer1(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {  // Consume 20 items
                buffer.consume();
                Thread.sleep(1000);  // Simulate time taken to consume an item
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Main class to run the producer and consumer threads
public class BoundedBufferApp {
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer(10);  // Buffer with capacity of 10

        // Create producer and consumer
        Producer1 producer = new Producer1(buffer);
        Consumer1 consumer = new Consumer1(buffer);

        // Create threads for producer and consumer
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        // Start the threads
        producerThread.start();
        consumerThread.start();

        // Wait for the threads to finish
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Producer and Consumer have finished execution.");
    }
}

