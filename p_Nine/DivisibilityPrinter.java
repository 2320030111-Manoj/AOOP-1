package p_Nine;

class DivisibilityPrinter {

    private int currentNumber = 1;
    private final int MAX_NUMBER = 15;

    // Method to print numbers divisible by 2
    public synchronized void printTwo() {
        while (currentNumber <= MAX_NUMBER) {
            if (currentNumber % 2 == 0 && currentNumber % 3 != 0 && currentNumber % 4 != 0 && currentNumber % 5 != 0) {
                System.out.println("Divisible by 2: " + currentNumber);
                currentNumber++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // Method to print numbers divisible by 3
    public synchronized void printThree() {
        while (currentNumber <= MAX_NUMBER) {
            if (currentNumber % 3 == 0 && currentNumber % 4 != 0 && currentNumber % 5 != 0) {
                System.out.println("Divisible by 3: " + currentNumber);
                currentNumber++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // Method to print numbers divisible by 4
    public synchronized void printFour() {
        while (currentNumber <= MAX_NUMBER) {
            if (currentNumber % 4 == 0 && currentNumber % 5 != 0) {
                System.out.println("Divisible by 4: " + currentNumber);
                currentNumber++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // Method to print numbers divisible by 5
    public synchronized void printFive() {
        while (currentNumber <= MAX_NUMBER) {
            if (currentNumber % 5 == 0) {
                System.out.println("Divisible by 5: " + currentNumber);
                currentNumber++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // Method to print numbers not divisible by 2, 3, 4, or 5
    public synchronized void printNumber() {
        while (currentNumber <= MAX_NUMBER) {
            if (currentNumber % 2 != 0 && currentNumber % 3 != 0 && currentNumber % 4 != 0 && currentNumber % 5 != 0) {
                System.out.println("Not divisible by 2, 3, 4, or 5: " + currentNumber);
                currentNumber++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // Main method to create and start threads
    public static void main(String[] args) {
        DivisibilityPrinter printer = new DivisibilityPrinter();

        Thread t1 = new Thread(printer::printTwo);
        Thread t2 = new Thread(printer::printThree);
        Thread t3 = new Thread(printer::printFour);
        Thread t4 = new Thread(printer::printFive);
        Thread t5 = new Thread(printer::printNumber);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

