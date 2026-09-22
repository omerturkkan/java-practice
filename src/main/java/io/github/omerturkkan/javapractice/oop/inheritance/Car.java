package io.github.omerturkkan.javapractice.oop.inheritance;

public class Car extends Vehicle {
    private final int doorCount;

    public Car(String brand, int year, int doorCount) {
        super(brand, year);          // parent constructor runs first
        this.doorCount = doorCount;
    }

    @Override
    public String describe() {
        return super.describe() + String.format(", %d doors", doorCount);
    }
}
