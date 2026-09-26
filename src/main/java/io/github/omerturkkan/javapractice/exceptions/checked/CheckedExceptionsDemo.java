package io.github.omerturkkan.javapractice.exceptions.checked;

public class CheckedExceptionsDemo {
    public static void main(String[] args) {
        // try-with-resources: close() runs automatically, even on failure
        System.out.println("happy path:");
        try (DatabaseConnection connection = new DatabaseConnection("db.local")) {
            System.out.println("  " + connection.query("select * from users"));
        } catch (ConnectionFailedException e) {
            System.out.println("  failed: " + e.getMessage());
        }

        System.out.println("\nquery rejected, resource still closed:");
        try (DatabaseConnection connection = new DatabaseConnection("db.local")) {
            System.out.println("  " + connection.query("drop table users"));
        } catch (ConnectionFailedException e) {
            System.out.printf("  failed on %s: %s%n", e.getHost(), e.getMessage());
        }

        System.out.println("\nconnection never opens:");
        try (DatabaseConnection connection = new DatabaseConnection("ghost.invalid")) {
            System.out.println("  " + connection.query("select 1"));
        } catch (ConnectionFailedException e) {
            System.out.printf("  failed on %s: %s%n", e.getHost(), e.getMessage());
        }

        // Wrapping a low-level exception in a domain one keeps the original cause
        System.out.println("\nexception chaining:");
        try {
            loadUserCount("not-a-number");
        } catch (ConnectionFailedException e) {
            System.out.println("  message : " + e.getMessage());
            System.out.println("  cause   : " + e.getCause());
        }
    }

    // 'throws' declares what this method may fail with; the caller must deal with it
    private static int loadUserCount(String raw) throws ConnectionFailedException {
        try {
            return Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            throw new ConnectionFailedException("db.local", "user count is not numeric", e);
        }
    }
}
