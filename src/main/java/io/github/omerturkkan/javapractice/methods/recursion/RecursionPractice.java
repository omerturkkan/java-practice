package io.github.omerturkkan.javapractice.methods.recursion;

import java.util.Arrays;

public class RecursionPractice {
    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        if (n <= 1) return 1;                      // base case
        return n * factorial(n - 1);               // recursive case
    }

    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static String reverse(String text) {
        if (text.length() <= 1) return text;
        return reverse(text.substring(1)) + text.charAt(0);
    }

    public static int digitSum(int n) {
        if (n < 10) return n;
        return n % 10 + digitSum(n / 10);
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int binarySearch(int[] sorted, int target, int low, int high) {
        if (low > high) return -1;
        int mid = (low + high) / 2;
        if (sorted[mid] == target) return mid;
        if (sorted[mid] < target) return binarySearch(sorted, target, mid + 1, high);
        return binarySearch(sorted, target, low, mid - 1);
    }

    public static void main(String[] args) {
        int[] sorted = {3, 8, 15, 23, 42, 56, 73, 91};

        System.out.printf("factorial(10)   : %d%n", factorial(10));
        System.out.printf("fibonacci(15)   : %d%n", fibonacci(15));
        System.out.printf("reverse(\"java\") : %s%n", reverse("java"));
        System.out.printf("digitSum(98765) : %d%n", digitSum(98765));
        System.out.printf("gcd(48, 18)     : %d%n", gcd(48, 18));

        int index = binarySearch(sorted, 42, 0, sorted.length - 1);
        System.out.printf("search 42 in %s : index %d%n", Arrays.toString(sorted), index);

        int missing = binarySearch(sorted, 50, 0, sorted.length - 1);
        System.out.printf("search 50       : index %d (not found)%n", missing);

        // Every call uses stack space; without a base case the stack overflows
        try {
            System.out.println(factorial(-1));
        } catch (IllegalArgumentException e) {
            System.out.println("factorial(-1)   : " + e.getMessage());
        }
    }
}
