package Seven;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class PriorityQueueTest {

    // Generic Priority Queue Class
    public static class PriorityQueue<T extends Comparable<T>> {
        private T[] heap;
        private int size;

        @SuppressWarnings("unchecked")
        public PriorityQueue(int capacity) {
            heap = (T[]) new Comparable[capacity];
            size = 0;
        }

        // Inserts a new element into the priority queue
        public void offer(T item) {
            if (size == heap.length) {
                resize(2 * heap.length);
            }
            heap[size] = item;
            siftUp(size);
            size++;
        }

        // Removes and returns the highest priority element (smallest for min-heap)
        public T poll() {
            if (isEmpty()) {
                throw new NoSuchElementException("Priority queue is empty");
            }
            T item = heap[0];
            heap[0] = heap[--size];
            heap[size] = null;  // Avoid memory leak
            siftDown(0);
            return item;
        }

        // Returns the highest priority element without removing it
        public T peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Priority queue is empty");
            }
            return heap[0];
        }

        // Checks if the priority queue is empty
        public boolean isEmpty() {
            return size == 0;
        }

        // Resizes the internal array when it's full
        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            heap = Arrays.copyOf(heap, newSize);
        }

        // Sift up to maintain heap property
        private void siftUp(int index) {
            while (index > 0) {
                int parentIndex = (index - 1) / 2;
                if (heap[index].compareTo(heap[parentIndex]) >= 0) {
                    break;
                }
                swap(index, parentIndex);
                index = parentIndex;
            }
        }

        // Sift down to maintain heap property
        private void siftDown(int index) {
            while (index * 2 + 1 < size) {
                int leftChild = index * 2 + 1;
                int rightChild = index * 2 + 2;
                int smallerChild = leftChild;

                if (rightChild < size && heap[rightChild].compareTo(heap[leftChild]) < 0) {
                    smallerChild = rightChild;
                }

                if (heap[index].compareTo(heap[smallerChild]) <= 0) {
                    break;
                }

                swap(index, smallerChild);
                index = smallerChild;
            }
        }

        // Swap two elements in the heap
        private void swap(int i, int j) {
            T temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }

    // Test the Priority Queue with different data types
    public static void main(String[] args) {
        // Test with Integer
        PriorityQueue<Integer> intQueue = new PriorityQueue<>(10);
        intQueue.offer(10);
        intQueue.offer(3);
        intQueue.offer(5);
        intQueue.offer(1);
        System.out.println("Integer Priority Queue Peek: " + intQueue.peek());  // Should print 1
        System.out.println("Integer Priority Queue Poll: " + intQueue.poll());  // Should print 1
        System.out.println("Integer Priority Queue Poll: " + intQueue.poll());  // Should print 3

        // Test with Double
        PriorityQueue<Double> doubleQueue = new PriorityQueue<>(10);
        doubleQueue.offer(5.5);
        doubleQueue.offer(2.2);
        doubleQueue.offer(9.9);
        doubleQueue.offer(1.1);
        System.out.println("Double Priority Queue Peek: " + doubleQueue.peek());  // Should print 1.1
        System.out.println("Double Priority Queue Poll: " + doubleQueue.poll());  // Should print 1.1
        System.out.println("Double Priority Queue Poll: " + doubleQueue.poll());  // Should print 2.2

        // Test with String
        PriorityQueue<String> stringQueue = new PriorityQueue<>(10);
        stringQueue.offer("banana");
        stringQueue.offer("apple");
        stringQueue.offer("orange");
        stringQueue.offer("pear");
        System.out.println("String Priority Queue Peek: " + stringQueue.peek());  // Should print "apple"
        System.out.println("String Priority Queue Poll: " + stringQueue.poll());  // Should print "apple"
        System.out.println("String Priority Queue Poll: " + stringQueue.poll());  // Should print "banana"
    }
}

