package Six;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CustomerSupportSystem {
    
    private Queue<String> ticketQueue;

    
    public CustomerSupportSystem() {
        ticketQueue = new LinkedList<>();
    }

    
    public void addTicket(String ticket) {
        ticketQueue.add(ticket);
        System.out.println("Ticket added successfully.");
    }

    
    public void processTicket() {
        if (!ticketQueue.isEmpty()) {
            String processedTicket = ticketQueue.poll(); // poll removes the next element
            System.out.println("Processing ticket: " + processedTicket);
        } else {
            System.out.println("No tickets to process.");
        }
    }

    
    public void displayTickets() {
        if (ticketQueue.isEmpty()) {
            System.out.println("No pending tickets.");
        } else {
            System.out.println("Pending tickets:");
            for (String ticket : ticketQueue) {
                System.out.println("- " + ticket);
            }
        }
    }

    public static void main(String[] args) {
        CustomerSupportSystem supportSystem = new CustomerSupportSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nCustomer Support Ticket System");
            System.out.println("1. Add Ticket");
            System.out.println("2. Process Ticket");
            System.out.println("3. Display Pending Tickets");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter ticket description: ");
                    String ticket = scanner.nextLine();
                    supportSystem.addTicket(ticket);
                    break;
                case 2:
                    supportSystem.processTicket();
                    break;
                case 3:
                    supportSystem.displayTickets();
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}

