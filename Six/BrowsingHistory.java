package Six;

import java.util.ArrayDeque;
import java.util.Deque;

class BrowserHistory {
    private Deque<String> backStack;    // Stack to store the backward history
    private Deque<String> forwardStack; // Stack to store the forward history
    private String currentPage;

    public BrowserHistory() {
        backStack = new ArrayDeque<>();
        forwardStack = new ArrayDeque<>();
        currentPage = null;
    }

    // Visit a new page
    public void visit(String page) {
        if (currentPage != null) {
            backStack.push(currentPage); // Push the current page to the back stack
        }
        currentPage = page; // Set the new page as the current page
        forwardStack.clear(); // Clear forward history since you visited a new page
        System.out.println("Visited: " + currentPage);
    }

    // Go back to the previous page
    public void back() {
        if (!backStack.isEmpty()) {
            forwardStack.push(currentPage); // Push current page to the forward stack
            currentPage = backStack.pop();  // Pop the last visited page from the back stack
            System.out.println("Back to: " + currentPage);
        } else {
            System.out.println("No pages to go back to.");
        }
    }

    // Go forward to the next page
    public void forward() {
        if (!forwardStack.isEmpty()) {
            backStack.push(currentPage);   // Push current page to the back stack
            currentPage = forwardStack.pop(); // Pop the next page from the forward stack
            System.out.println("Forward to: " + currentPage);
        } else {
            System.out.println("No pages to go forward to.");
        }
    }

    // Display the current page
    public void displayCurrentPage() {
        if (currentPage != null) {
            System.out.println("Current Page: " + currentPage);
        } else {
            System.out.println("No page is currently open.");
        }
    }

    public static void main(String[] args) {
        BrowserHistory history = new BrowserHistory();
        
        history.visit("google.com");
        history.visit("facebook.com");
        history.visit("twitter.com");
        
        history.back();
        history.back();
        
        history.forward();
        
        history.displayCurrentPage();
        
        history.visit("youtube.com");
        
        history.displayCurrentPage();
    }
}

