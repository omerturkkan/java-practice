package io.github.omerturkkan.javapractice.basics.strings;

import java.util.Scanner;

public class LoginChecker {

    public static void main(String[] args) {
        String storedUsername = "john_doe";
        String storedPassword = "Secret123";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String enteredUsername = scanner.nextLine();

        System.out.print("Password: ");
        String enteredPassword = scanner.nextLine();

        scanner.close();

        String cleanedUsername = enteredUsername.trim();
        boolean isUsernameEmpty = cleanedUsername.isEmpty();
        boolean usernameMatch = storedUsername.equalsIgnoreCase(cleanedUsername);
        boolean passwordMatch = storedPassword.equals(enteredPassword);
        boolean endsWithUnderscore = cleanedUsername.endsWith("_");
        boolean loginSuccessful = usernameMatch && passwordMatch;

        System.out.println("Entered username  : \"" + enteredUsername + "\"");
        System.out.println("Cleaned username  : \"" + cleanedUsername + "\"");
        System.out.println("Is empty          : " + isUsernameEmpty);
        System.out.println("Username match    : " + usernameMatch);
        System.out.println("Password match    : " + passwordMatch);
        System.out.println("Ends with '_'     : " + endsWithUnderscore);
        System.out.println("Login successful  : " + loginSuccessful);
    }
}