package io.github.omerturkkan.javapractice.oop.abstraction;

public class EmailNotification extends Notification {
    private final String subject;

    public EmailNotification(String address, String subject) {
        super(address);
        this.subject = subject;
    }

    @Override
    protected String channel() {
        return "EMAIL";
    }

    @Override
    protected String format(String message) {
        return subject + " | " + message;
    }

    @Override
    protected int maxLength() {
        return 120;
    }
}
