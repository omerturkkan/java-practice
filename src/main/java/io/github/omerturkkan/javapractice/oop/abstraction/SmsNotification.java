package io.github.omerturkkan.javapractice.oop.abstraction;

public class SmsNotification extends Notification {
    public SmsNotification(String phoneNumber) {
        super(phoneNumber);
    }

    @Override
    protected String channel() {
        return "SMS";
    }

    @Override
    protected String format(String message) {
        return message.toUpperCase();
    }

    @Override
    protected int maxLength() {
        return 40;
    }
}
