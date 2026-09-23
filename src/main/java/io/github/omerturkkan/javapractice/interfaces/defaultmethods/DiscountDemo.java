package io.github.omerturkkan.javapractice.interfaces.defaultmethods;

public class DiscountDemo {
    public static void main(String[] args) {
        Book book = new Book("Effective Java", 850);
        Electronics laptop = new Electronics("ThinkPad X1", 42000, false);
        Electronics oldPhone = new Electronics("Pixel 6", 12000, true);

        System.out.printf("%-16s %s%n", book.getTitle(), book.label());
        System.out.printf("%-16s %s%n", laptop.getModel(), laptop.label());
        System.out.printf("%-16s %s%n", oldPhone.getModel(), oldPhone.label());

        System.out.printf("%nClearance rate asked : %.0f%%, cap : %.0f%%%n",
                oldPhone.discountRate() * 100, Discountable.MAX_DISCOUNT_RATE * 100);

        // Static interface method, called on the interface itself
        System.out.printf("Cart total : %.2f%n", Discountable.totalOf(book, laptop, oldPhone));
    }
}
