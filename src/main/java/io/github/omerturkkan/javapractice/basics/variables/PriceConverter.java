package io.github.omerturkkan.javapractice.basics.variables;

public class PriceConverter {

    public static void main(String[] args) {
        String rawPriceText = "1499.99";

        double exactPrice = Double.parseDouble(rawPriceText);
        int roundedPrice = (int) exactPrice;

        System.out.println("Raw Price Text: " + rawPriceText);
        System.out.println("Exact Price: " + exactPrice);
        System.out.println("Rounded Price: " + roundedPrice);
    }
}