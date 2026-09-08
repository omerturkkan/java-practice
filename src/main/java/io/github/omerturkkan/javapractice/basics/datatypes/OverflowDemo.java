package io.github.omerturkkan.javapractice.basics.datatypes;

public class OverflowDemo {
    public static void main(String[] args) {
        int maxInt = Integer.MAX_VALUE;
        int overflowed = maxInt + 1;

        System.out.printf("Max int       : %d%n", maxInt);
        System.out.printf("Max int + 1   : %d%n", overflowed);

        byte maxByte = 127;
        byte overflowedByte = (byte) (maxByte + 1);

        System.out.printf("Max byte      : %d%n", maxByte);
        System.out.printf("Max byte + 1  : %d%n", overflowedByte);

        int a = 1_000_000;
        int b = 1_000_000;
        long wrongResult = a * b;
        long correctResult = (long) a * b;

        System.out.printf("int * int     : %d%n", wrongResult);
        System.out.printf("(long) * int  : %d%n", correctResult);

        float floatVal = 0.1f + 0.2f;
        double doubleVal = 0.1 + 0.2;

        System.out.printf("float 0.1+0.2 : %.20f%n", floatVal);
        System.out.printf("double 0.1+0.2: %.20f%n", doubleVal);
    }
}