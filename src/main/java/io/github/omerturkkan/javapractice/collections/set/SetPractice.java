package io.github.omerturkkan.javapractice.collections.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetPractice {
    // A record gets equals() and hashCode() for free, so it works in a HashSet
    record Product(String name, int price) {
    }

    public static void main(String[] args) {
        List<String> raw = List.of("java", "kotlin", "java", "go", "rust", "go", "java");

        Set<String> unique = new HashSet<>(raw);          // duplicates dropped, order undefined
        Set<String> ordered = new LinkedHashSet<>(raw);   // insertion order kept
        TreeSet<String> sorted = new TreeSet<>(raw);      // natural order

        System.out.println("raw       : " + raw);
        System.out.println("hash      : " + unique);
        System.out.println("linked    : " + ordered);
        System.out.println("tree      : " + sorted);

        // TreeSet adds navigation methods the others do not have
        System.out.printf("first: %s, last: %s, higher than 'go': %s%n",
                sorted.first(), sorted.last(), sorted.higher("go"));
        System.out.println("headSet   : " + sorted.headSet("kotlin"));

        // Set algebra
        Set<String> backend = new HashSet<>(List.of("java", "go", "rust", "python"));
        Set<String> mobile = new HashSet<>(List.of("java", "kotlin", "swift"));

        Set<String> both = new TreeSet<>(backend);
        both.retainAll(mobile);
        Set<String> onlyBackend = new TreeSet<>(backend);
        onlyBackend.removeAll(mobile);
        Set<String> all = new TreeSet<>(backend);
        all.addAll(mobile);

        System.out.printf("%nintersection : %s%n", both);
        System.out.println("difference   : " + onlyBackend);
        System.out.println("union        : " + all);

        // Equal values collapse into one entry because equals/hashCode match
        Set<Product> products = new HashSet<>();
        products.add(new Product("mouse", 450));
        products.add(new Product("mouse", 450));
        products.add(new Product("mouse", 500));
        System.out.printf("%nproducts     : %d entries -> %s%n", products.size(), products);
    }
}
