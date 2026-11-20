package io.github.omerturkkan.javapractice.designpatterns.structural;

public class FacadePractice {

    static class StockService {
        boolean isInStock(String product) {
            System.out.println("Checking stock for " + product);
            return !product.equalsIgnoreCase("tablet");
        }
    }

    static class PaymentService {
        void charge(String customer, double amount) {
            System.out.printf("Charging %s %.2f TL%n", customer, amount);
        }
    }

    static class ShippingService {
        void ship(String product, String customer) {
            System.out.println("Shipping " + product + " to " + customer);
        }
    }

    // single entry point, the caller doesn't need to know the order of the steps
    static class OrderFacade {
        private final StockService stock = new StockService();
        private final PaymentService payment = new PaymentService();
        private final ShippingService shipping = new ShippingService();

        boolean placeOrder(String customer, String product, double price) {
            if (!stock.isInStock(product)) {
                System.out.println(product + " is out of stock");
                return false;
            }
            payment.charge(customer, price);
            shipping.ship(product, customer);
            return true;
        }
    }

    public static void main(String[] args) {
        OrderFacade orders = new OrderFacade();

        boolean first = orders.placeOrder("Ali", "Laptop", 25000);
        System.out.println("Order completed: " + first);
        System.out.println();

        boolean second = orders.placeOrder("Ayse", "Tablet", 12000);
        System.out.println("Order completed: " + second);
    }
}
