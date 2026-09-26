package io.github.omerturkkan.javapractice.exceptions.unchecked;

public class UncheckedExceptionsDemo {
    public static void main(String[] args) {
        int[] scores = {10, 20, 30};

        // ArrayIndexOutOfBoundsException
        try {
            System.out.println(scores[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("index    : " + e.getMessage());
        }

        // ArithmeticException: only integer division throws, doubles give Infinity
        try {
            System.out.println(10 / 0);
        } catch (ArithmeticException e) {
            System.out.println("division : " + e.getMessage());
        }
        System.out.println("10.0/0   : " + (10.0 / 0));

        // NullPointerException
        String nothing = null;
        try {
            System.out.println(nothing.length());
        } catch (NullPointerException e) {
            System.out.println("null     : caught NullPointerException");
        }

        // Multi-catch: one block for several unrelated types
        for (String input : new String[]{"42", "abc", null}) {
            try {
                // trim() on null throws NPE, parseInt on "abc" throws NumberFormatException
                System.out.printf("parsed '%s' -> %d%n", input, Integer.parseInt(input.trim()));
            } catch (NumberFormatException | NullPointerException e) {
                System.out.printf("failed '%s' -> %s%n", input, e.getClass().getSimpleName());
            }
        }

        // finally always runs, even when the try block returns
        System.out.println("result   : " + riskyOperation(4));
        System.out.println("result   : " + riskyOperation(0));

        // Unchecked exceptions need no throws clause and no catch
        System.out.println("uncaught exceptions would stop the program here");
    }

    private static String riskyOperation(int divisor) {
        try {
            int value = 100 / divisor;
            return "ok, value=" + value;
        } catch (ArithmeticException e) {
            return "recovered from " + e.getClass().getSimpleName();
        } finally {
            System.out.println("finally  : cleanup for divisor " + divisor);
        }
    }
}
