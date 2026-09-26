package io.github.omerturkkan.javapractice.exceptions.custom;

// Checked: the caller is forced to decide what to do about it
public class InsufficientFundsException extends Exception {
    private final double requested;
    private final double available;

    public InsufficientFundsException(double requested, double available) {
        super(String.format("requested %.2f but only %.2f available", requested, available));
        this.requested = requested;
        this.available = available;
    }

    // A custom exception can carry data, not just a message
    public double getShortfall() {
        return requested - available;
    }
}
