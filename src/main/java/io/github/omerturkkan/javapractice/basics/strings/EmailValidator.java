package io.github.omerturkkan.javapractice.basics.strings;

public class EmailValidator {

    public static void main(String[] args) {
        String email = "  John.Doe@Example.COM  ";
        String cleaned = email.trim().toLowerCase();

        int atIndex = cleaned.indexOf("@");

        boolean hasAt = cleaned.contains("@");
        boolean hasDotAfterAt = cleaned.indexOf(".", atIndex) != -1;
        boolean isValid = hasAt && hasDotAfterAt;

        String username = cleaned.substring(0, atIndex);
        String domain = cleaned.substring(atIndex + 1);

        System.out.println("Raw input : \"" + email + "\"");
        System.out.println("Cleaned   : \"" + cleaned + "\"");
        System.out.println("Valid     : " + isValid);
        System.out.println("Username  : " + username);
        System.out.println("Domain    : " + domain);
    }
}