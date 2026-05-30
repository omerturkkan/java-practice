package io.github.omerturkkan.javapractice.basics.datatypes;

public class PayrollCalculator {
    public static void main(String[] args) {
        int employeeId = 100245;
        String employeeName = "John Doe";
        char grade = 'B';
        double hourlyRate = 78.125;
        int hoursWorked = 160;
        boolean isManager = true;
        double taxRate = 0.20;
        double managerBonusRate = 0.15;

        double grossSalary = hourlyRate * hoursWorked;
        double afterManagerBonus = isManager
                ? grossSalary * (1 + managerBonusRate)
                : grossSalary;
        double netSalary = afterManagerBonus * (1 - taxRate);
        double annualGross = afterManagerBonus * 12;
        char nextGrade = (char) (grade + 1);

        long totalEmployees = 2_500_000_000L;
        double totalMonthlyPayroll = totalEmployees * netSalary;

        double precisionTest = 0.1 + 0.2;

        System.out.println("Employee ID         : " + employeeId);
        System.out.println("Name                : " + employeeName);
        System.out.println("Grade               : " + grade);
        System.out.println("Is Manager          : " + isManager);
        System.out.println("Hourly Rate         : $" + hourlyRate);
        System.out.println("Hours Worked        : " + hoursWorked);
        System.out.println("Gross Salary        : $" + grossSalary);
        System.out.println("After Manager Bonus : $" + afterManagerBonus);
        System.out.println("Net Salary          : $" + netSalary);
        System.out.println("Annual Gross        : $" + annualGross);
        System.out.println("Next Grade          : " + nextGrade);
        System.out.println("Total Monthly Payroll (2.5B employees) : $" + totalMonthlyPayroll);
        System.out.println("Precision Test (0.1 + 0.2) : " + precisionTest);
    }
}