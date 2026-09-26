package io.github.omerturkkan.javapractice.exceptions.custom;

public class CustomExceptionDemo {
    public static void main(String[] args) {
        PaymentService service = new PaymentService(500);

        attempt(service, "4242424242424242", 120);
        attempt(service, "4242424242424242", 1000);
        attempt(service, "1234", 50);
        attempt(service, "42424242abcd4242", 50);

        // Catching the parent type also catches the subclass
        try {
            service.charge("4242424242424242", 99999);
        } catch (Exception e) {
            System.out.printf("%ncaught as Exception : %s%n", e.getClass().getSimpleName());
        }
    }

    private static void attempt(PaymentService service, String card, double amount) {
        try {
            System.out.printf("%-8.2f %s%n", amount, service.charge(card, amount));
        } catch (InsufficientFundsException e) {
            // Checked: the compiler required this catch
            System.out.printf("%-8.2f declined - %s (short by %.2f)%n", amount, e.getMessage(), e.getShortfall());
        } catch (InvalidCardException e) {
            // Unchecked: catching is optional, but useful here
            System.out.printf("%-8.2f invalid card - %s%n", amount, e.getMessage());
        }
    }
}
