package io.github.omerturkkan.javapractice.functional.streams;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPractice {
    record Order(String customer, String city, String category, double amount, int quantity) {
    }

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Omer", "Istanbul", "electronics", 4200.00, 1),
                new Order("Ayse", "Ankara", "books", 185.50, 3),
                new Order("Kaan", "Istanbul", "electronics", 899.90, 2),
                new Order("Selin", "Izmir", "kitchen", 640.00, 1),
                new Order("Omer", "Istanbul", "books", 92.75, 1),
                new Order("Ayse", "Ankara", "kitchen", 1250.00, 2),
                new Order("Deniz", "Izmir", "electronics", 15900.00, 1),
                new Order("Kaan", "Istanbul", "books", 310.25, 5)
        );

        // filter -> map -> collect: the classic pipeline
        List<String> bigSpenders = orders.stream()
                .filter(order -> order.amount() > 1000)
                .map(Order::customer)
                .distinct()
                .sorted()
                .toList();
        System.out.println("over 1000     : " + bigSpenders);

        // Terminal operations that reduce to a single value
        double revenue = orders.stream().mapToDouble(Order::amount).sum();
        long istanbulCount = orders.stream().filter(order -> order.city().equals("Istanbul")).count();
        boolean anyHuge = orders.stream().anyMatch(order -> order.amount() > 10000);
        boolean allPositive = orders.stream().allMatch(order -> order.quantity() > 0);

        System.out.printf("revenue       : %.2f%n", revenue);
        System.out.printf("istanbul      : %d orders%n", istanbulCount);
        System.out.printf("anyMatch>10k  : %b, allMatch qty>0 : %b%n", anyHuge, allPositive);

        // Optional: max() may find nothing, so it never returns null
        Optional<Order> largest = orders.stream().max(Comparator.comparingDouble(Order::amount));
        largest.ifPresent(order -> System.out.printf("largest       : %s %.2f%n", order.customer(), order.amount()));

        Optional<Order> missing = orders.stream().filter(order -> order.city().equals("Bursa")).findFirst();
        System.out.println("bursa order   : " + missing.map(Order::customer).orElse("none"));

        // groupingBy: one map entry per key
        Map<String, Long> countByCity = orders.stream()
                .collect(Collectors.groupingBy(Order::city, Collectors.counting()));
        Map<String, Double> revenueByCategory = orders.stream()
                .collect(Collectors.groupingBy(Order::category, Collectors.summingDouble(Order::amount)));

        System.out.printf("%ncount by city : %s%n", countByCity);
        System.out.println("revenue/cat   : " + revenueByCategory);

        // Nested grouping, and a downstream mapping collector
        Map<String, List<String>> customersByCity = orders.stream()
                .collect(Collectors.groupingBy(Order::city,
                        Collectors.mapping(Order::customer, Collectors.toSet())))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream().sorted().toList()));
        System.out.println("customers     : " + customersByCity);

        // Statistics in one pass
        DoubleSummaryStatistics stats = orders.stream()
                .collect(Collectors.summarizingDouble(Order::amount));
        System.out.printf("%nstats         : count=%d min=%.2f avg=%.2f max=%.2f%n",
                stats.getCount(), stats.getMin(), stats.getAverage(), stats.getMax());

        // reduce: fold the stream into one value by hand
        String receipt = orders.stream()
                .filter(order -> order.customer().equals("Kaan"))
                .map(order -> String.format("%s(%.2f)", order.category(), order.amount()))
                .reduce("Kaan:", (left, right) -> left + " " + right);
        System.out.println("reduce        : " + receipt);

        // flatMap flattens nested structures
        List<String> words = Stream.of("java streams", "are lazy", "until terminal")
                .flatMap(line -> Stream.of(line.split(" ")))
                .toList();
        System.out.println("flatMap       : " + words);

        // Streams are lazy: the pipeline is built here but nothing runs yet
        Stream<String> lazy = orders.stream()
                .filter(order -> order.city().equals("Izmir"))
                .peek(order -> System.out.println("  touched " + order.customer()))
                .map(Order::category);
        System.out.println("\npipeline built, nothing printed above; now calling toList():");
        System.out.println("  result -> " + lazy.toList());

        // count() may skip the pipeline entirely when it can size the source directly,
        // so peek() never runs here
        System.out.println("counting with peek:");
        long counted = orders.stream().peek(order -> System.out.println("  never printed")).count();
        System.out.printf("counted       : %d (peek was optimised away)%n", counted);

        // Primitive streams and generated ranges
        System.out.printf("%nsquares       : %s%n",
                IntStream.rangeClosed(1, 8).map(n -> n * n).boxed().toList());
        System.out.printf("joined        : %s%n",
                orders.stream().map(Order::customer).distinct().collect(Collectors.joining(", ", "[", "]")));
    }
}
