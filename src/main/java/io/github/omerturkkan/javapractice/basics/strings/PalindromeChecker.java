package io.github.omerturkkan.javapractice.basics.strings;

public class PalindromeChecker {
    public static void main(String[] args) {
        String original = "A man a plan a canal Panama";

        String cleaned = original.replaceAll("[^a-zA-Z]", "").toLowerCase();

        String reversed = new StringBuilder(cleaned).reverse().toString();

        boolean isPalindrome = cleaned.equals(reversed);

        System.out.printf("Original  : %s%n", original);
        System.out.printf("Cleaned   : %s%n", cleaned);
        System.out.printf("Reversed  : %s%n", reversed);
        System.out.printf("Palindrome: %s%n", isPalindrome);
    }
}