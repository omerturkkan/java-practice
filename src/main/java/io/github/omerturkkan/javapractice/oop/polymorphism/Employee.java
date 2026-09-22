package io.github.omerturkkan.javapractice.oop.polymorphism;

public class Employee {
    protected final String name;
    protected final double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public double bonus() {
        return baseSalary * 0.05;
    }

    public String role() {
        return "Employee";
    }

    public final double totalPay() {
        return baseSalary + bonus();
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10s base=%8.2f bonus=%7.2f total=%8.2f",
                name, role(), baseSalary, bonus(), totalPay());
    }
}
