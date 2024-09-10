package Six;

import java.util.*;

public class ToDoList {

	public static void main(String[] args) {
		 ArrayList<String> tasks = new ArrayList<>();
	        Scanner scanner = new Scanner(System.in);
	        int choice;

	        do {
	            System.out.println("\nTo-Do List");
	            System.out.println("1. Add Task");
	            System.out.println("2. Update Task");
	            System.out.println("3. Remove Task");
	            System.out.println("4. Display Tasks");
	            System.out.println("5. Exit");
	            System.out.print("Choose an option: ");
	            choice = scanner.nextInt();
	            scanner.nextLine();  

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter task: ");
	                    tasks.add(scanner.nextLine());
	                    break;
	                case 2:
	                    System.out.print("Enter task number to update: ");
	                    int updateIndex = scanner.nextInt() - 1;
	                    scanner.nextLine();  // Consume newline
	                    if (updateIndex >= 0 && updateIndex < tasks.size()) {
	                        System.out.print("Enter new description: ");
	                        tasks.set(updateIndex, scanner.nextLine());
	                    } else {
	                        System.out.println("Invalid task number.");
	                    }
	                    break;
	                case 3:
	                    System.out.print("Enter task number to remove: ");
	                    int removeIndex = scanner.nextInt() - 1;
	                    if (removeIndex >= 0 && removeIndex < tasks.size()) {
	                        tasks.remove(removeIndex);
	                    } else {
	                        System.out.println("Invalid task number.");
	                    }
	                    break;
	                case 4:
	                    System.out.println("Your tasks:");
	                    for (int i = 0; i < tasks.size(); i++) {
	                        System.out.println((i + 1) + ". " + tasks.get(i));
	                    }
	                    break;
	                case 5:
	                    System.out.println("Exiting...");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        } while (choice != 5);

	        scanner.close();
	    }
	

	}


