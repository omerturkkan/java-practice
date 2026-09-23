package io.github.omerturkkan.javapractice.interfaces.multipleinheritance;

public interface Printer {
    void print(String document);

    default String status() {
        return "Printer ready";
    }

    default String supplies() {
        return "toner 60%";
    }
}
