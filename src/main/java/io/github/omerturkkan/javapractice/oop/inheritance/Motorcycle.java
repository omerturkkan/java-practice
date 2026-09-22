package io.github.omerturkkan.javapractice.oop.inheritance;

public class Motorcycle extends Vehicle {
    private final boolean hasSidecar;

    public Motorcycle(String brand, int year, boolean hasSidecar) {
        super(brand, year);
        this.hasSidecar = hasSidecar;
    }

    @Override
    public int wheelCount() {
        return hasSidecar ? 3 : 2;
    }

    @Override
    public void accelerate(int amount) {
        // Lighter vehicle: the same input produces more speed
        super.accelerate(amount * 2);
    }

    @Override
    public String describe() {
        return super.describe() + (hasSidecar ? ", with sidecar" : ", solo");
    }
}
