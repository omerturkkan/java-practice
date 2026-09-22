package io.github.omerturkkan.javapractice.oop.inheritance;

public class Vehicle {
    protected final String brand;
    protected final int year;
    protected int speed;

    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    public void accelerate(int amount) {
        speed += amount;
    }

    public String describe() {
        return String.format("%d %s at %d km/h", year, brand, speed);
    }

    public int wheelCount() {
        return 4;
    }
}
