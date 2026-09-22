package io.github.omerturkkan.javapractice.oop.polymorphism;

public class Manager extends Employee {
    private final int teamSize;

    public Manager(String name, double baseSalary, int teamSize) {
        super(name, baseSalary);
        this.teamSize = teamSize;
    }

    @Override
    public double bonus() {
        return baseSalary * 0.15 + teamSize * 500;
    }

    @Override
    public String role() {
        return "Manager";
    }
}
