package Seven;

interface Stack<T> {
    void push(T item);
    T pop();
    T peek();
    boolean isEmpty();
}

//Linked List Stack Implementation
class LinkedListStack<T> implements Stack<T> {
 private class Node {
     T data;
     Node next;

     Node(T data) {
         this.data = data;
         this.next = null;
     }
 }

 private Node top;

 public LinkedListStack() {
     top = null;
 }

 @Override
 public void push(T item) {
     Node newNode = new Node(item);
     newNode.next = top;
     top = newNode;
 }

 @Override
 public T pop() {
     if (isEmpty()) {
         throw new RuntimeException("Stack is empty");
     }
     T item = top.data;
     top = top.next;
     return item;
 }

 @Override
 public T peek() {
     if (isEmpty()) {
         throw new RuntimeException("Stack is empty");
     }
     return top.data;
 }

 @Override
 public boolean isEmpty() {
     return top == null;
 }
}

//Array Stack Implementation
class ArrayStack<T> implements Stack<T> {
 private T[] stack;
 private int size;
 private int top;

 @SuppressWarnings("unchecked")
 public ArrayStack(int capacity) {
     stack = (T[]) new Object[capacity];
     top = -1;
     size = capacity;
 }

 @Override
 public void push(T item) {
     if (top == size - 1) {
         resize(2 * size); // Resize array if full
     }
     stack[++top] = item;
 }

 @Override
 public T pop() {
     if (isEmpty()) {
         throw new RuntimeException("Stack is empty");
     }
     T item = stack[top--];
     stack[top + 1] = null; // Avoid memory leak
     return item;
 }

 @Override
 public T peek() {
     if (isEmpty()) {
         throw new RuntimeException("Stack is empty");
     }
     return stack[top];
 }

 @Override
 public boolean isEmpty() {
     return top == -1;
 }

 @SuppressWarnings("unchecked")
 private void resize(int newSize) {
     T[] newArray = (T[]) new Object[newSize];
     System.arraycopy(stack, 0, newArray, 0, size);
     size = newSize;
     stack = newArray;
 }
}

public class StackTest {
    public static void main(String[] args) {
        // Test LinkedListStack with Integer
        Stack<Integer> linkedListStack = new LinkedListStack<>();
        linkedListStack.push(1);
        linkedListStack.push(2);
        linkedListStack.push(3);
        System.out.println("LinkedListStack Peek: " + linkedListStack.peek()); // Should print 3
        System.out.println("LinkedListStack Pop: " + linkedListStack.pop()); // Should print 3
        System.out.println("LinkedListStack isEmpty: " + linkedListStack.isEmpty()); // Should print false

        // Test ArrayStack with String
        Stack<String> arrayStack = new ArrayStack<>(5);
        arrayStack.push("Hello");
        arrayStack.push("World");
        System.out.println("ArrayStack Peek: " + arrayStack.peek()); // Should print "World"
        System.out.println("ArrayStack Pop: " + arrayStack.pop()); // Should print "World"
        System.out.println("ArrayStack isEmpty: " + arrayStack.isEmpty()); // Should print false

        // Test ArrayStack with Double
        Stack<Double> arrayStackDouble = new ArrayStack<>(3);
        arrayStackDouble.push(1.1);
        arrayStackDouble.push(2.2);
        arrayStackDouble.push(3.3);
        System.out.println("ArrayStackDouble Peek: " + arrayStackDouble.peek()); // Should print 3.3
        System.out.println("ArrayStackDouble Pop: " + arrayStackDouble.pop()); // Should print 3.3
        System.out.println("ArrayStackDouble isEmpty: " + arrayStackDouble.isEmpty()); // Should print false
    }
}
