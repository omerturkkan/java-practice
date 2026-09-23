package io.github.omerturkkan.javapractice.interfaces.functional;

// Exactly one abstract method, so a lambda can implement it
@FunctionalInterface
public interface TextTransformer {
    String transform(String input);

    // Defaults are allowed: they do not break the single-abstract-method rule
    default TextTransformer andThen(TextTransformer next) {
        return input -> next.transform(this.transform(input));
    }

    static TextTransformer identity() {
        return input -> input;
    }
}
