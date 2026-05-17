package io.github.omerturkkan.javapractice.basics.variables;

public class BoundaryOverflow {

    public static void main(String[] args) {
        int maxValidId = Integer.MAX_VALUE;
        int overflowedId = maxValidId + 1;

        byte maxByteCapacity = 127;
        byte overflowedByte = (byte) (maxByteCapacity + 1);

        System.out.println("--- Integer Boundary Test ---");
        System.out.println("Max Valid ID: " + maxValidId);
        System.out.println("Overflowed ID: " + overflowedId);

        System.out.println();
        System.out.println("--- Byte Boundary Test ---");
        System.out.println("Max Byte Capacity: " + maxByteCapacity);
        System.out.println("Overflowed Byte: " + overflowedByte);
    }
}