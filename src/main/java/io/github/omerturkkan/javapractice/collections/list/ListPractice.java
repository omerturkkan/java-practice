package io.github.omerturkkan.javapractice.collections.list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListPractice {
    public static void main(String[] args) {
        List<String> cities = new ArrayList<>(List.of("Istanbul", "Ankara", "Izmir", "Bursa"));

        cities.add("Antalya");
        cities.add(1, "Adana");          // insert at index
        cities.remove("Bursa");

        System.out.println("cities      : " + cities);
        System.out.printf("size        : %d, indexOf Izmir: %d%n", cities.size(), cities.indexOf("Izmir"));
        System.out.printf("contains    : %b, first: %s, last: %s%n",
                cities.contains("Ankara"), cities.get(0), cities.get(cities.size() - 1));

        // Sorting with a Comparator instead of natural order
        List<String> byLength = new ArrayList<>(cities);
        byLength.sort(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()));
        System.out.println("by length   : " + byLength);

        // Removing during a loop: only the iterator may do it safely
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() % 3 == 0) iterator.remove();
        }
        System.out.println("no multiples of 3: " + numbers);

        // removeIf does the same in one line
        List<Integer> copy = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        copy.removeIf(n -> n % 2 == 0);
        System.out.println("odds only        : " + copy);

        // subList is a view: changing it changes the backing list
        List<Integer> view = numbers.subList(0, 3);
        view.set(0, 99);
        System.out.println("after view edit  : " + numbers);

        // ArrayList: fast random access. LinkedList: fast insert/remove at the ends
        LinkedList<String> queue = new LinkedList<>(List.of("first", "second"));
        queue.addFirst("zero");
        queue.addLast("third");
        System.out.println("linked list      : " + queue);

        // List.of is immutable
        try {
            List.of("a", "b").add("c");
        } catch (UnsupportedOperationException e) {
            System.out.println("List.of          : immutable, add() rejected");
        }
    }
}
