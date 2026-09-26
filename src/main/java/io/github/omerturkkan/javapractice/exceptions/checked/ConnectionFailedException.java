package io.github.omerturkkan.javapractice.exceptions.checked;

// extends Exception (not RuntimeException) -> checked: callers must handle or declare it
public class ConnectionFailedException extends Exception {
    private final String host;

    public ConnectionFailedException(String host, String message) {
        super(message);
        this.host = host;
    }

    public ConnectionFailedException(String host, String message, Throwable cause) {
        super(message, cause);      // keeps the original exception as the cause
        this.host = host;
    }

    public String getHost() {
        return host;
    }
}
