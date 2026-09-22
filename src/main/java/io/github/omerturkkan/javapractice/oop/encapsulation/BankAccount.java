package io.github.omerturkkan.javapractice.oop.encapsulation;

public class BankAccount {
    private final String iban;
    private final String owner;
    private double balance;
    private int transactionCount;

    public BankAccount(String iban, String owner, double openingBalance) {
        if (iban == null || iban.isBlank()) throw new IllegalArgumentException("iban is required");
        if (openingBalance < 0) throw new IllegalArgumentException("opening balance cannot be negative");
        this.iban = iban;
        this.owner = owner;
        this.balance = openingBalance;
    }

    public void deposit(double amount) {
        requirePositive(amount);
        balance += amount;
        transactionCount++;
    }

    public boolean withdraw(double amount) {
        requirePositive(amount);
        if (amount > balance) return false;
        balance -= amount;
        transactionCount++;
        return true;
    }

    // No setBalance: the field can only change through deposit/withdraw
    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }

    public String getMaskedIban() {
        return "****" + iban.substring(iban.length() - 4);
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    private static void requirePositive(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount must be positive");
    }
}
