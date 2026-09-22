package io.github.omerturkkan.javapractice.oop.polymorphism;

public class PayrollDemo {
    public static void main(String[] args) {
        // One array, three different runtime types
        Employee[] staff = {
                new Employee("Ayse", 40000),
                new Developer("Omer", 60000, 4),
                new Manager("Selin", 75000, 6),
                new Developer("Kaan", 55000, 1)
        };

        double payroll = 0;
        for (Employee employee : staff) {
            // The compiler sees Employee; the JVM calls the subclass version
            System.out.println(employee);
            payroll += employee.totalPay();
        }

        System.out.printf("%nTotal payroll : %.2f%n", payroll);
        System.out.printf("Highest paid  : %s%n", highestPaid(staff).name);

        // Pattern matching for instanceof (Java 16+)
        for (Employee employee : staff) {
            if (employee instanceof Manager manager) {
                System.out.printf("%s manages a team, bonus %.2f%n", manager.name, manager.bonus());
            }
        }
    }

    private static Employee highestPaid(Employee[] staff) {
        Employee best = staff[0];
        for (Employee employee : staff) {
            if (employee.totalPay() > best.totalPay()) best = employee;
        }
        return best;
    }
}
