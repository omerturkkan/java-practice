package io.github.omerturkkan.javapractice.interfaces.defaultmethods;

public interface Discountable {
    // Interface fields are implicitly public static final
    double MAX_DISCOUNT_RATE = 0.50;

    double basePrice();

    double discountRate();

    // default: subclasses inherit this body and may override it
    default double finalPrice() {
        double rate = Math.min(discountRate(), MAX_DISCOUNT_RATE);
        return basePrice() * (1 - rate);
    }

    default String label() {
        return String.format("%.2f -> %.2f (%.0f%% off)",
                basePrice(), finalPrice(), Math.min(discountRate(), MAX_DISCOUNT_RATE) * 100);
    }

    // static: belongs to the interface, not to implementations
    static double totalOf(Discountable... items) {
        double total = 0;
        for (Discountable item : items) total += item.finalPrice();
        return total;
    }
}
