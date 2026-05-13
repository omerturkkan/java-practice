package io.github.omerturkkan.javapractice.basics.variables;

public class EmployeeProfile {

    public static void main(String[] args) {
        String fullName = "John Doe";
        int age = 26;
        char genderInitial = 'M';

        double salary = 2800.0;
        boolean isFullTime = true;

        final int CURRENT_YEAR = 2026;
        int birthYear = CURRENT_YEAR - age;

        System.out.println("--- Employee Profile ---");
        System.out.println("Name: " + fullName);
        System.out.println("Age: " + age);
        System.out.println("Salary: $" + salary);
        System.out.println("Gender: " + genderInitial);
        System.out.println("Full Time: " + isFullTime);
        System.out.println("Date of Birth: " + birthYear);
        System.out.println("------------------------");
    }
}