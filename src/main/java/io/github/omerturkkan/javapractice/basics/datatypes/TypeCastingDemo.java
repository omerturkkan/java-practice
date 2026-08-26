package io.github.omerturkkan.javapractice.basics.datatypes;

import java.util.Scanner;

public class TypeCastingDemo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a double: ");
        double input = scanner.nextDouble();

        int narrowed = (int) input;
        byte asByte = (byte) narrowed;

        System.out.println("double : " + input);
        System.out.println("to int : " + narrowed);
        System.out.println("to byte: " + asByte);

        System.out.print("Enter an integer: ");
        int num = scanner.nextInt();

        System.out.println("to double: " + (double) num);
        System.out.println("to float : " + (float) num);
        System.out.println("to char  : " + (char) num);

        scanner.close();
    }
}