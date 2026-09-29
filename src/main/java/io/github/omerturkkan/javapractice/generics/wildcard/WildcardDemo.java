package io.github.omerturkkan.javapractice.generics.wildcard;

import java.util.ArrayList;
import java.util.List;

public class WildcardDemo {
    // PECS - Producer Extends: the list is read from, never written to
    public static double total(List<? extends Number> numbers) {
        double sum = 0;
        for (Number number : numbers) sum += number.doubleValue();
        // numbers.add(1); would not compile: the exact element type is unknown
        return sum;
    }

    // PECS - Consumer Super: the list is written to
    public static void fillWithSquares(List<? super Integer> target, int count) {
        for (int i = 1; i <= count; i++) target.add(i * i);
        // Integer value = target.get(0); would not compile: only Object is guaranteed
    }

    // Unbounded wildcard: the element type does not matter at all
    public static int sizeOf(List<?> anyList) {
        return anyList.size();
    }

    // Without a wildcard, only the exact type is accepted
    public static double totalStrict(List<Number> numbers) {
        double sum = 0;
        for (Number number : numbers) sum += number.doubleValue();
        return sum;
    }

    public static void main(String[] args) {
        List<Integer> ints = List.of(1, 2, 3, 4);
        List<Double> doubles = List.of(1.5, 2.5);
        List<Number> mixed = List.of(1, 2.5, 3L);

        // All three are accepted thanks to ? extends Number
        System.out.printf("total ints    : %.1f%n", total(ints));
        System.out.printf("total doubles : %.1f%n", total(doubles));
        System.out.printf("total mixed   : %.1f%n", total(mixed));

        // totalStrict(ints) would not compile: List<Integer> is not a List<Number>
        System.out.printf("strict mixed  : %.1f%n", totalStrict(mixed));

        List<Number> numbers = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        fillWithSquares(numbers, 5);
        fillWithSquares(objects, 3);
        System.out.printf("%nsquares into List<Number> : %s%n", numbers);
        System.out.printf("squares into List<Object> : %s%n", objects);

        System.out.printf("%nsizeOf ints   : %d%n", sizeOf(ints));
        System.out.printf("sizeOf strings: %d%n", sizeOf(List.of("a", "b", "c")));

        // A List<Integer> is not a subtype of List<Number>, even though Integer is a Number
        List<? extends Number> readOnly = ints;
        System.out.printf("%nfirst element : %s (reading is fine)%n", readOnly.get(0));
        System.out.println("readOnly.add(5) would be a compile error");
    }
}
