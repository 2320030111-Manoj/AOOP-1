package p_Nine;

//BankAccount class to handle balance and transactions
class BankAccount {

 private double balance;

 // Constructor to initialize the balance
 public BankAccount(double initialBalance) {
     this.balance = initialBalance;
 }

 // Method to deposit money into the account (synchronized for thread safety)
 public synchronized void deposit(double amount) {
     if (amount > 0) {
         balance += amount;
         System.out.println(Thread.currentThread().getName() + " deposited $" + amount + ". Current balance: $" + balance);
     } else {
         System.out.println("Invalid deposit amount");
     }
 }

 // Method to withdraw money from the account (synchronized for thread safety)
 public synchronized void withdraw(double amount) {
     if (amount > 0 && amount <= balance) {
         balance -= amount;
         System.out.println(Thread.currentThread().getName() + " withdrew $" + amount + ". Current balance: $" + balance);
     } else if (amount > balance) {
         System.out.println(Thread.currentThread().getName() + " tried to withdraw $" + amount + " but insufficient funds. Current balance: $" + balance);
     } else {
         System.out.println("Invalid withdraw amount");
     }
 }

 // Method to get the current balance
 public synchronized double getBalance() {
     return balance;
 }
}

//User class representing a user performing transactions on the account
class User implements Runnable {

 private BankAccount account;
 private String transactionType;
 private double amount;

 // Constructor to create a User with transaction type and amount
 public User(BankAccount account, String transactionType, double amount) {
     this.account = account;
     this.transactionType = transactionType;
     this.amount = amount;
 }

 // Run method to perform the transaction
 @Override
 public void run() {
     if ("deposit".equalsIgnoreCase(transactionType)) {
         account.deposit(amount);
     } else if ("withdraw".equalsIgnoreCase(transactionType)) {
         account.withdraw(amount);
     } else {
         System.out.println("Invalid transaction type");
     }
 }
}

//Main class to simulate multiple users performing transactions concurrently
public class BankAccountManagement {

 public static void main(String[] args) {
     // Create a shared bank account with an initial balance of 1000
     BankAccount sharedAccount = new BankAccount(1000);

     // Create multiple users with various transactions
     User user1 = new User(sharedAccount, "deposit", 200);
     User user2 = new User(sharedAccount, "withdraw", 150);
     User user3 = new User(sharedAccount, "withdraw", 1200); // Insufficient funds scenario
     User user4 = new User(sharedAccount, "deposit", 500);
     User user5 = new User(sharedAccount, "withdraw", 300);

     // Create threads for each user transaction
     Thread t1 = new Thread(user1, "User 1");
     Thread t2 = new Thread(user2, "User 2");
     Thread t3 = new Thread(user3, "User 3");
     Thread t4 = new Thread(user4, "User 4");
     Thread t5 = new Thread(user5, "User 5");

     // Start the threads to perform transactions concurrently
     t1.start();
     t2.start();
     t3.start();
     t4.start();
     t5.start();

     // Wait for all threads to complete
     try {
         t1.join();
         t2.join();
         t3.join();
         t4.join();
         t5.join();
     } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
     }

     // Print final balance
     System.out.println("Final balance: $" + sharedAccount.getBalance());
 }
}

