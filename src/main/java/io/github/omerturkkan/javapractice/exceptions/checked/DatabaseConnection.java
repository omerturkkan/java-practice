package io.github.omerturkkan.javapractice.exceptions.checked;

// AutoCloseable lets this class be used in try-with-resources
public class DatabaseConnection implements AutoCloseable {
    private final String host;
    private boolean open;

    public DatabaseConnection(String host) throws ConnectionFailedException {
        if (host == null || host.isBlank()) {
            throw new ConnectionFailedException(host, "host is required");
        }
        if (host.endsWith(".invalid")) {
            throw new ConnectionFailedException(host, "unknown host");
        }
        this.host = host;
        this.open = true;
        System.out.printf("  opened connection to %s%n", host);
    }

    public String query(String sql) throws ConnectionFailedException {
        if (!open) throw new ConnectionFailedException(host, "connection already closed");
        if (sql.toLowerCase().startsWith("drop")) {
            throw new ConnectionFailedException(host, "statement rejected: " + sql);
        }
        return "3 rows for '" + sql + "'";
    }

    @Override
    public void close() {
        open = false;
        System.out.printf("  closed connection to %s%n", host);
    }
}
