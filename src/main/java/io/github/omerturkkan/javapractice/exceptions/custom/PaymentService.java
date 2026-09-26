package io.github.omerturkkan.javapractice.exceptions.custom;

public class PaymentService {
    private double balance;

    public PaymentService(double balance) {
        this.balance = balance;
    }

    public String charge(String cardNumber, double amount) throws InsufficientFundsException {
        validateCard(cardNumber);
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        balance -= amount;
        return String.format("charged %.2f, remaining %.2f", amount, balance);
    }

    private void validateCard(String cardNumber) {
        if (cardNumber == null || cardNumber.length() != 16) {
            throw new InvalidCardException("card number must be 16 digits");
        }
        if (!cardNumber.chars().allMatch(Character::isDigit)) {
            throw new InvalidCardException("card number must contain digits only");
        }
    }
}
