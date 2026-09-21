package io.github.omerturkkan.javapractice.methods.overloading;

public class AreaCalculator {
    public static double area(double side) {
        return side * side;
    }

    public static double area(double width, double height) {
        return width * height;
    }

    // Same name, different parameter type: the compiler picks by signature
    public static double area(int radius) {
        return Math.PI * radius * radius;
    }

    public static double area(double base, double height, boolean isTriangle) {
        return isTriangle ? base * height / 2 : base * height;
    }

    public static String describe(double value) {
        return String.format("%.2f cm2", value);
    }

    public static String describe(double value, String unit) {
        return String.format("%.2f %s", value, unit);
    }

    public static void main(String[] args) {
        double square = area(5.0);
        double rectangle = area(4.0, 6.0);
        double circle = area(3);
        double triangle = area(8.0, 3.0, true);

        // long widens to double when no exact match exists
        double widened = area(7L);

        System.out.println("Square    : " + describe(square));
        System.out.println("Rectangle : " + describe(rectangle));
        System.out.println("Circle    : " + describe(circle));
        System.out.println("Triangle  : " + describe(triangle));
        System.out.println("Widened   : " + describe(widened, "cm2 (long -> double)"));
    }
}
