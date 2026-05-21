package io.github.omerturkkan.javapractice.basics.strings;

public class NameFormatter {

    public static void main(String[] args) {
        String fullName = "  jOhN    doE  ";

        String trimmed = fullName.trim();
        String[] parts = trimmed.split(" +");

        String firstName = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1).toLowerCase();
        String lastName = parts[1].toUpperCase();
        String formatted = lastName + ", " + firstName;

        System.out.println("Raw input    : \"" + fullName + "\"");
        System.out.println("Trimmed      : \"" + trimmed + "\"");
        System.out.println("First name   : " + firstName);
        System.out.println("Last name    : " + lastName);
        System.out.println("Formatted    : " + formatted);
        System.out.println("Character count (formatted): " + formatted.length());
    }
}