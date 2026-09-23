package io.github.omerturkkan.javapractice.interfaces.defaultmethods;

public class Electronics implements Discountable {
    private final String model;
    private final double price;
    private final boolean clearance;

    public Electronics(String model, double price, boolean clearance) {
        this.model = model;
        this.price = price;
        this.clearance = clearance;
    }

    @Override
    public double basePrice() {
        return price;
    }

    @Override
    public double discountRate() {
        return clearance ? 0.70 : 0.10;   // 0.70 gets capped by MAX_DISCOUNT_RATE
    }

    // Overriding a default method: shipping is added on top
    @Override
    public double finalPrice() {
        return Discountable.super.finalPrice() + 49.90;
    }

    public String getModel() {
        return model;
    }
}
