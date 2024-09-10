package Six;
import java.util.*;
public class Task {
	
	private String description;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}


class TaskManagementSystem {

    private List<Task> tasks;

    public TaskManagementSystem() {
        tasks = new ArrayList<>();
    }

    // Add a new task to the list
    public void addTask(String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
        System.out.println("Task added: " + description);
    }

    // Update a task's description by its position
    public void updateTask(int position, String newDescription) {
        if (position >= 0 && position < tasks.size()) {
            tasks.get(position).setDescription(newDescription);
            System.out.println("Task updated at position " + position + ": " + newDescription);
        } else {
            System.out.println("Invalid position.");
        }
    }

    // Remove a task by its position
    public void removeTask(int position) {
        if (position >= 0 && position < tasks.size()) {
            tasks.remove(position);
            System.out.println("Task removed at position " + position);
        } else {
            System.out.println("Invalid position.");
        }
    }

    // Display all tasks in the list
    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ": " + tasks.get(i).getDescription());
            }
        }
    }

    // Main method to interact with the system
    public static void main(String[] args) {
        TaskManagementSystem system = new TaskManagementSystem();
        Scanner scanner = new Scanner(System.in);
        String command;
        
        do {
            System.out.println("\nEnter command (add, update, remove, display, exit):");
            command = scanner.nextLine();

            switch (command) {
                case "add":
                    System.out.println("Enter task description:");
                    String description = scanner.nextLine();
                    system.addTask(description);
                    break;

                case "update":
                    System.out.println("Enter task position to update:");
                    int updatePos = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.println("Enter new task description:");
                    String newDescription = scanner.nextLine();
                    system.updateTask(updatePos, newDescription);
                    break;

                case "remove":
                    System.out.println("Enter task position to remove:");
                    int removePos = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    system.removeTask(removePos);
                    break;

                case "display":
                    system.displayTasks();
                    break;

                case "exit":
                    System.out.println("Exiting system.");
                    break;

                default:
                    System.out.println("Unknown command.");
                    break;
            }
        } while (!command.equals("exit"));

        scanner.close();
    }
}

