package io.github.omerturkkan.javapractice.basics.scanner;

import java.util.Scanner;

public class UnitConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 - km to miles");
        System.out.println("2 - kg to pounds");
        System.out.println("3 - Celsius to Fahrenheit");
        System.out.print("Choose: ");
        int choice = scanner.nextInt();

        System.out.print("Enter value: ");
        double value = scanner.nextDouble();

        String fromUnit;
        String toUnit;
        double result;

        switch (choice) {
            case 1 -> { fromUnit = "km"; toUnit = "miles"; result = value * 0.621371; }
            case 2 -> { fromUnit = "kg"; toUnit = "lbs"; result = value * 2.20462; }
            case 3 -> { fromUnit = "°C"; toUnit = "°F"; result = value * 9.0 / 5 + 32; }
            default -> { fromUnit = "?"; toUnit = "?"; result = 0; }
        }

        System.out.printf("%.2f %s = %.2f %s%n", value, fromUnit, result, toUnit);

        scanner.close();
    }
}