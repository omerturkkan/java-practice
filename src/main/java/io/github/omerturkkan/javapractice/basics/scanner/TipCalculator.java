package io.github.omerturkkan.javapractice.basics.scanner;

import java.util.Scanner;

public class TipCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Bill amount: ");
        double billAmount = scanner.nextDouble();

        System.out.print("Tip percentage: ");
        int tipPercentage = scanner.nextInt();

        System.out.print("Split between how many people: ");
        int people = scanner.nextInt();

        double tipAmount = billAmount * tipPercentage / 100.0;
        double totalAmount = billAmount + tipAmount;
        double perPerson = totalAmount / people;

        System.out.printf("%nBill    : %.2f%n", billAmount);
        System.out.printf("Tip %d%%  : %.2f%n", tipPercentage, tipAmount);
        System.out.printf("Total   : %.2f%n", totalAmount);
        System.out.printf("Each    : %.2f%n", perPerson);

        scanner.close();
    }
}