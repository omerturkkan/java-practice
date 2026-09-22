package io.github.omerturkkan.javapractice.oop.abstraction;

public abstract class Notification {
    private final String recipient;

    protected Notification(String recipient) {
        this.recipient = recipient;
    }

    // No body: every subclass must supply its own
    protected abstract String channel();

    protected abstract String format(String message);

    protected abstract int maxLength();

    // Template method: the shared flow lives here, the details are delegated
    public final String send(String message) {
        String body = format(message);
        if (body.length() > maxLength()) {
            body = body.substring(0, maxLength() - 3) + "...";
        }
        return String.format("[%s -> %s] %s", channel(), recipient, body);
    }

    public String getRecipient() {
        return recipient;
    }
}
