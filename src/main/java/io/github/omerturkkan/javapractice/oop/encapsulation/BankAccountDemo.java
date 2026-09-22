package io.github.omerturkkan.javapractice.oop.encapsulation;

public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("TR330006100519786457841326", "Omer Turkkan", 1000);

        account.deposit(500);
        boolean firstWithdraw = account.withdraw(300);
        boolean secondWithdraw = account.withdraw(5000);

        System.out.printf("Owner        : %s%n", account.getOwner());
        System.out.printf("IBAN         : %s%n", account.getMaskedIban());
        System.out.printf("Withdraw 300 : %b%n", firstWithdraw);
        System.out.printf("Withdraw 5000: %b (insufficient funds)%n", secondWithdraw);
        System.out.printf("Balance      : %.2f%n", account.getBalance());
        System.out.printf("Transactions : %d%n", account.getTransactionCount());

        // The object protects its own state: invalid input never reaches the field
        try {
            account.deposit(-50);
        } catch (IllegalArgumentException e) {
            System.out.println("deposit(-50) : " + e.getMessage());
        }

        try {
            new BankAccount("", "Nobody", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("empty iban   : " + e.getMessage());
        }
    }
}
