package Six;

import java.util.Arrays;

//Define a generic interface for finding min and max values
interface MinMax1<T extends Comparable<T>> {
 T min();  // Method to find the minimum value
 T max();  // Method to find the maximum value
}

//Generic class implementing the MinMax interface
class ArrayMinMax<T extends Comparable<T>> implements MinMax1<T> {
 private T[] array;  // The generic array

 // Constructor to accept the array
 public ArrayMinMax(T[] array) {
     this.array = array;
 }

 // Method to find the minimum value in the array
 public T min() {
     T minValue = array[0];
     for (T value : array) {
         if (value.compareTo(minValue) < 0) {
             minValue = value;
         }
     }
     return minValue;
 }

 // Method to find the maximum value in the array
 public T max() {
     T maxValue = array[0];
     for (T value : array) {
         if (value.compareTo(maxValue) > 0) {
             maxValue = value;
         }
     }
     return maxValue;
 }
}

//Driver class to test the generic implementation
class MinMax {
 public static void main(String[] args) {
     // Integer array example
     Integer[] intArray = {3, 7, 2, 9, 4};
     ArrayMinMax<Integer> intMinMax = new ArrayMinMax<>(intArray);
     System.out.println("Integer Array: " + Arrays.toString(intArray));
     System.out.println("Min Integer: " + intMinMax.min());
     System.out.println("Max Integer: " + intMinMax.max());

     // Float array example
     Float[] floatArray = {4.3f, 1.2f, 6.7f, 2.9f};
     ArrayMinMax<Float> floatMinMax = new ArrayMinMax<>(floatArray);
     System.out.println("\nFloat Array: " + Arrays.toString(floatArray));
     System.out.println("Min Float: " + floatMinMax.min());
     System.out.println("Max Float: " + floatMinMax.max());

     // String array example
     String[] stringArray = {"apple", "orange", "banana", "grape"};
     ArrayMinMax<String> stringMinMax = new ArrayMinMax<>(stringArray);
     System.out.println("\nString Array: " + Arrays.toString(stringArray));
     System.out.println("Min String: " + stringMinMax.min());
     System.out.println("Max String: " + stringMinMax.max());

     // Character array example
     Character[] charArray = {'a', 'z', 'd', 'k', 'm'};
     ArrayMinMax<Character> charMinMax = new ArrayMinMax<>(charArray);
     System.out.println("\nCharacter Array: " + Arrays.toString(charArray));
     System.out.println("Min Character: " + charMinMax.min());
     System.out.println("Max Character: " + charMinMax.max());
 }
}
