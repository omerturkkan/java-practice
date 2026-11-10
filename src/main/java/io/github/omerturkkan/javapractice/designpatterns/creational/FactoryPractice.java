package io.github.omerturkkan.javapractice.designpatterns.creational;

public class FactoryPractice {

    // Common type: the caller only knows this interface, not the concrete classes
    interface Notification {
        void send(String message);
    }

    static class EmailNotification implements Notification {
        @Override
        public void send(String message) {
            System.out.println("EMAIL -> " + message);
        }
    }

    static class SmsNotification implements Notification {
        @Override
        public void send(String message) {
            System.out.println("SMS   -> " + message);
        }
    }

    static class PushNotification implements Notification {
        @Override
        public void send(String message) {
            System.out.println("PUSH  -> " + message);
        }
    }

    // The factory: the only place that decides which concrete class to create
    static class NotificationFactory {
        static Notification create(String channel) {
            return switch (channel.toLowerCase()) {
                case "email" -> new EmailNotification();
                case "sms" -> new SmsNotification();
                case "push" -> new PushNotification();
                default -> throw new IllegalArgumentException("Unknown channel: " + channel);
            };
        }
    }

    public static void main(String[] args) {
        String[] channels = {"email", "SMS", "push"};

        for (String channel : channels) {
            Notification notification = NotificationFactory.create(channel);
            notification.send("Your order has been shipped");
        }

        try {
            NotificationFactory.create("fax");
        } catch (IllegalArgumentException e) {
            System.out.println("Error -> " + e.getMessage());
        }
    }
}
