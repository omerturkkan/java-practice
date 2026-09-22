package io.github.omerturkkan.javapractice.oop.polymorphism;

public class Developer extends Employee {
    private final int shippedProjects;

    public Developer(String name, double baseSalary, int shippedProjects) {
        super(name, baseSalary);
        this.shippedProjects = shippedProjects;
    }

    @Override
    public double bonus() {
        return super.bonus() + shippedProjects * 1000;
    }

    @Override
    public String role() {
        return "Developer";
    }
}
