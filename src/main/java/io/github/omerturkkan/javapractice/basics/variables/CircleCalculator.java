package io.github.omerturkkan.javapractice.basics.variables;

public class CircleCalculator {

    public static void main(String[] args) {
        final double PI = Math.PI;
        int radius = 7;
        int diameter;
        double circumference;
        double area;

        diameter = 2 * radius;
        circumference = 2 * PI * radius;
        area = PI * radius * radius;

        System.out.println("=== Circle Calculator ===");
        System.out.println("Radius       : " + radius);
        System.out.println("Diameter     : " + diameter);
        System.out.println("Circumference: " + circumference);
        System.out.println("Area         : " + area);
    }
}
