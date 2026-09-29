package io.github.omerturkkan.javapractice.generics.genericclass;

import java.util.List;

public class GenericClassDemo {
    record User(String name, int age) {
    }

    public static void main(String[] args) {
        Pair<String, Integer> score = new Pair<>("Omer", 92);
        Pair<Integer, String> swapped = score.swap();
        Pair<String, Boolean> passed = score.withValue(true);

        System.out.println("pair      : " + score);
        System.out.println("swapped   : " + swapped);
        System.out.println("withValue : " + passed);
        System.out.printf("key type  : %s, value type: %s%n",
                score.getKey().getClass().getSimpleName(),
                score.getValue().getClass().getSimpleName());

        // Same class, two different element types, both checked at compile time
        Repository<User> users = new Repository<>();
        users.save(new User("Omer", 30));
        users.save(new User("Ayse", 24));
        users.save(new User("Kaan", 41));

        Repository<String> tags = new Repository<>();
        tags.save("java");
        tags.save("generics");
        tags.save("practice");

        System.out.printf("%nusers     : %d -> %s%n", users.count(), users);
        System.out.println("over 30   : " + users.findAll(user -> user.age() > 30));
        System.out.println("find Ayse : " + users.findFirst(user -> user.name().equals("Ayse")));
        System.out.println("find Zeko : " + users.findFirst(user -> user.name().equals("Zeko")));
        System.out.println("tags      : " + tags.findAll(tag -> tag.length() > 4));

        // No cast needed: the compiler already knows the element type
        User oldest = users.findAll(user -> user.age() > 40).get(0);
        System.out.printf("oldest    : %s (%d)%n", oldest.name(), oldest.age());

        // Type erasure: at runtime both lists are just List
        List<String> a = List.of("x");
        List<Integer> b = List.of(1);
        System.out.printf("%nerasure   : %s == %s -> %b%n",
                a.getClass().getSimpleName(), b.getClass().getSimpleName(),
                a.getClass() == b.getClass());
    }
}
