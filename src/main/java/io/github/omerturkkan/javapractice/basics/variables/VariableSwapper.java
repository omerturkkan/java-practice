package io.github.omerturkkan.javapractice.basics.variables;

public class VariableSwapper {

    public static void main(String[] args) {
        int x = 10;
        int y = 25;

        System.out.println("--- Before Swap ---");
        System.out.println("x: " + x);
        System.out.println("y: " + y);

        x = x + y;
        y = x - y;
        x = x - y;

        System.out.println();
        System.out.println("--- After Swap ---");
        System.out.println("x: " + x);
        System.out.println("y: " + y);
    }
}