package io.github.omerturkkan.javapractice.interfaces.defaultmethods;

public class Book implements Discountable {
    private final String title;
    private final double price;

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public double basePrice() {
        return price;
    }

    @Override
    public double discountRate() {
        return 0.20;
    }

    public String getTitle() {
        return title;
    }
}
