package io.github.omerturkkan.javapractice.methods.varargs;

import java.util.Arrays;

public class StatisticsToolkit {
    public static int sum(int... numbers) {
        int total = 0;
        for (int number : numbers) total += number;
        return total;
    }

    public static double average(int... numbers) {
        if (numbers.length == 0) return 0;
        return (double) sum(numbers) / numbers.length;
    }

    public static int max(int first, int... rest) {
        int result = first;
        for (int number : rest) {
            if (number > result) result = number;
        }
        return result;
    }

    // varargs must be the last parameter
    public static String report(String label, double... values) {
        return String.format("%-10s count=%d values=%s", label, values.length, Arrays.toString(values));
    }

    public static void main(String[] args) {
        System.out.printf("sum()            : %d%n", sum());
        System.out.printf("sum(5)           : %d%n", sum(5));
        System.out.printf("sum(1,2,3,4,5)   : %d%n", sum(1, 2, 3, 4, 5));

        int[] scores = {72, 85, 90, 65, 95};
        // An array can be passed straight to a varargs method
        System.out.printf("sum(array)       : %d%n", sum(scores));
        System.out.printf("average(array)   : %.1f%n", average(scores));
        System.out.printf("max(3, 17, 8)    : %d%n", max(3, 17, 8));
        System.out.printf("max(42)          : %d%n", max(42));

        System.out.println(report("empty"));
        System.out.println(report("temps", 21.5, 23.0, 19.8));
    }
}
