package io.github.omerturkkan.javapractice.oop.abstraction;

public class NotificationDemo {
    public static void main(String[] args) {
        String message = "Your order has been shipped and will arrive on Friday";

        Notification[] channels = {
                new SmsNotification("+90 555 000 11 22"),
                new EmailNotification("omer@example.com", "Order update")
        };

        for (Notification notification : channels) {
            System.out.println(notification.send(message));
        }

        // An abstract class cannot be instantiated:
        // Notification n = new Notification("x");   // compile error

        // ...but an anonymous subclass can fill in the missing methods
        Notification push = new Notification("device-9134") {
            @Override
            protected String channel() {
                return "PUSH";
            }

            @Override
            protected String format(String text) {
                return "* " + text;
            }

            @Override
            protected int maxLength() {
                return 25;
            }
        };

        System.out.println(push.send(message));
        System.out.printf("%nRecipient of push : %s%n", push.getRecipient());
    }
}
