package io.github.omerturkkan.javapractice.interfaces.functional;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TransformerDemo {
    public static void main(String[] args) {
        String raw = "  Merhaba Dunya  ";

        // Same interface, three ways to supply the implementation
        TextTransformer anonymous = new TextTransformer() {
            @Override
            public String transform(String input) {
                return input.trim();
            }
        };
        TextTransformer lambda = input -> input.replace(" ", "-");
        TextTransformer methodReference = String::toLowerCase;

        TextTransformer pipeline = anonymous.andThen(methodReference).andThen(lambda);

        System.out.printf("raw       : '%s'%n", raw);
        System.out.printf("trimmed   : '%s'%n", anonymous.transform(raw));
        System.out.printf("pipeline  : '%s'%n", pipeline.transform(raw));
        System.out.printf("identity  : '%s'%n", TextTransformer.identity().transform(raw));

        // Built-in functional interfaces from java.util.function
        Predicate<String> isLong = text -> text.length() > 10;
        Function<String, Integer> wordCount = text -> text.trim().split("\\s+").length;
        Supplier<String> defaultName = () -> "anonymous";
        Consumer<String> printUpper = text -> System.out.println("consumer  : " + text.toUpperCase());
        BiFunction<String, String, String> join = (a, b) -> a + " " + b;

        System.out.printf("%nisLong    : %b%n", isLong.test(raw));
        System.out.printf("wordCount : %d%n", wordCount.apply(raw));
        System.out.printf("supplier  : %s%n", defaultName.get());
        printUpper.accept("lambda captured this text");
        System.out.printf("biFunction: %s%n", join.apply("Java", "17"));

        // Predicates combine
        Predicate<String> hasSpace = text -> text.contains(" ");
        System.out.printf("long AND has space : %b%n", isLong.and(hasSpace).test(raw));
        System.out.printf("negated isLong     : %b%n", isLong.negate().test("kisa"));
    }
}
