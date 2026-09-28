package io.github.omerturkkan.javapractice.collections.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapPractice {
    public static void main(String[] args) {
        Map<String, Integer> stock = new HashMap<>();
        stock.put("keyboard", 12);
        stock.put("mouse", 40);
        stock.put("monitor", 5);
        stock.put("mouse", 35);          // same key overwrites

        System.out.println("stock        : " + stock);
        System.out.printf("mouse        : %d%n", stock.get("mouse"));
        System.out.printf("missing key  : %s%n", stock.get("webcam"));
        System.out.printf("getOrDefault : %d%n", stock.getOrDefault("webcam", 0));

        stock.putIfAbsent("webcam", 8);          // only adds when absent
        stock.putIfAbsent("mouse", 999);         // ignored, key exists
        stock.merge("monitor", 3, Integer::sum); // combine old and new value
        stock.computeIfPresent("keyboard", (key, value) -> value - 2);

        System.out.println("after edits  : " + new TreeMap<>(stock));

        // Iterating over entries
        System.out.println("\nlow stock (< 10):");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            if (entry.getValue() < 10) {
                System.out.printf("  %-10s %d%n", entry.getKey(), entry.getValue());
            }
        }

        // Word frequency: the classic map exercise
        String text = "the quick brown fox jumps over the lazy dog the fox";
        Map<String, Integer> frequency = new TreeMap<>();
        for (String word : text.split(" ")) {
            frequency.merge(word, 1, Integer::sum);
        }
        System.out.println("\nfrequency    : " + frequency);

        // computeIfAbsent builds the inner list only when the key is new
        Map<Integer, List<String>> byLength = new TreeMap<>();
        for (String word : text.split(" ")) {
            byLength.computeIfAbsent(word.length(), key -> new ArrayList<>()).add(word);
        }
        System.out.println("grouped      : " + byLength);

        // LinkedHashMap keeps insertion order, TreeMap sorts by key
        Map<String, String> capitals = new LinkedHashMap<>();
        capitals.put("Turkey", "Ankara");
        capitals.put("Japan", "Tokyo");
        capitals.put("Brazil", "Brasilia");
        System.out.println("\nlinked       : " + capitals);
        System.out.println("tree         : " + new TreeMap<>(capitals));
        System.out.println("keys         : " + capitals.keySet());
        System.out.println("values       : " + capitals.values());
    }
}
