package io.github.omerturkkan.javapractice.arrays;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] numbers = {42, 17, 8, 99, 23, 4, 56, 15, 73, 31};
        int[] original = numbers.clone();

        int passes = 0;
        int swaps = 0;
        int comparisons = 0;

        for (int i = 0; i < numbers.length - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < numbers.length - 1 - i; j++) {
                comparisons++;
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        int[] expected = original.clone();
        Arrays.sort(expected);

        System.out.println("Original    : " + Arrays.toString(original));
        System.out.println("Sorted      : " + Arrays.toString(numbers));
        System.out.printf("Passes      : %d%n", passes);
        System.out.printf("Comparisons : %d%n", comparisons);
        System.out.printf("Swaps       : %d%n", swaps);
        System.out.printf("Matches Arrays.sort : %b%n", Arrays.equals(numbers, expected));
    }
}
