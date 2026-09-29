package io.github.omerturkkan.javapractice.generics.bounded;

import java.util.List;

public class BoundedTypesDemo {
    // T must implement Comparable, otherwise compareTo would not exist
    public static <T extends Comparable<T>> T max(List<T> items) {
        if (items.isEmpty()) throw new IllegalArgumentException("list is empty");
        T best = items.get(0);
        for (T item : items) {
            if (item.compareTo(best) > 0) best = item;
        }
        return best;
    }

    // Upper bound on a class: any Number works, and its methods are available
    public static <T extends Number> double sum(List<T> numbers) {
        double total = 0;
        for (T number : numbers) total += number.doubleValue();
        return total;
    }

    // Multiple bounds: class first, then interfaces, joined with &
    public static <T extends Number & Comparable<T>> String range(List<T> numbers) {
        T min = numbers.get(0);
        T max = numbers.get(0);
        for (T number : numbers) {
            if (number.compareTo(min) < 0) min = number;
            if (number.compareTo(max) > 0) max = number;
        }
        return String.format("%s..%s (span %.2f)", min, max, max.doubleValue() - min.doubleValue());
    }

    // The type parameter is inferred from the arguments
    public static <T> void printAll(String label, List<T> items) {
        System.out.printf("%-12s %s%n", label, items);
    }

    // A bounded type parameter on a class
    static class Measurement<T extends Number> {
        private final String unit;
        private final T value;

        Measurement(String unit, T value) {
            this.unit = unit;
            this.value = value;
        }

        boolean isAbove(double threshold) {
            return value.doubleValue() > threshold;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static void main(String[] args) {
        List<Integer> ages = List.of(30, 24, 41, 19);
        List<String> names = List.of("Omer", "Ayse", "Kaan");
        List<Double> prices = List.of(19.9, 4.5, 132.0, 87.25);

        printAll("ages", ages);
        printAll("names", names);
        printAll("prices", prices);

        System.out.printf("%nmax age    : %d%n", max(ages));
        System.out.printf("max name   : %s (alphabetical)%n", max(names));
        System.out.printf("max price  : %.2f%n", max(prices));

        System.out.printf("%nsum ages   : %.0f%n", sum(ages));
        System.out.printf("sum prices : %.2f%n", sum(prices));
        System.out.println("range ages : " + range(ages));

        Measurement<Double> temperature = new Measurement<>("C", 38.5);
        Measurement<Integer> pulse = new Measurement<>("bpm", 72);
        System.out.printf("%n%s above 37 : %b%n", temperature, temperature.isAbove(37));
        System.out.printf("%s above 100: %b%n", pulse, pulse.isAbove(100));

        // Measurement<String> would not compile: String is not a Number
        // sum(names) would not compile either
    }
}
