package io.github.omerturkkan.javapractice.designpatterns.creational;

import java.time.Duration;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class BuilderPractice {

    // Immutable product: final fields, no setters, only the builder can create it
    static final class HttpRequest {
        private final String method;
        private final String url;
        private final Map<String, String> headers;
        private final String body;
        private final Duration timeout;
        private final int maxRetries;

        private HttpRequest(Builder builder) {
            this.method = builder.method;
            this.url = builder.url;
            // Defensive copy: later changes to the builder cannot leak into this object
            this.headers = Collections.unmodifiableMap(new LinkedHashMap<>(builder.headers));
            this.body = builder.body;
            this.timeout = builder.timeout;
            this.maxRetries = builder.maxRetries;
        }

        // Required parameters go into the entry point, optional ones get fluent methods
        static Builder newBuilder(String url) {
            return new Builder(url);
        }

        // Copy an existing request into a builder to create a modified variant
        Builder toBuilder() {
            Builder builder = new Builder(url)
                    .body(body)
                    .timeout(timeout)
                    .maxRetries(maxRetries);
            builder.method = method;
            builder.headers.putAll(headers);
            return builder;
        }

        Map<String, String> headers() {
            return headers;
        }

        @Override
        public String toString() {
            return String.format("%s %s%n  headers : %s%n  body    : %s%n  timeout : %ds, retries: %d",
                    method, url, headers, body == null ? "<none>" : body, timeout.toSeconds(), maxRetries);
        }

        static final class Builder {
            private final String url;
            private String method = "GET";
            private final Map<String, String> headers = new LinkedHashMap<>();
            private String body;
            private Duration timeout = Duration.ofSeconds(30);
            private int maxRetries = 0;

            private Builder(String url) {
                this.url = Objects.requireNonNull(url, "url must not be null");
            }

            // Each method returns 'this' so calls can be chained
            Builder get() {
                this.method = "GET";
                return this;
            }

            Builder post(String body) {
                this.method = "POST";
                this.body = body;
                return this;
            }

            Builder header(String name, String value) {
                headers.put(name, value);
                return this;
            }

            Builder body(String body) {
                this.body = body;
                return this;
            }

            Builder timeout(Duration timeout) {
                this.timeout = timeout;
                return this;
            }

            Builder maxRetries(int maxRetries) {
                this.maxRetries = maxRetries;
                return this;
            }

            // Validation happens once, right before the object is created
            HttpRequest build() {
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    throw new IllegalStateException("url must start with http:// or https://");
                }
                if (method.equals("GET") && body != null) {
                    throw new IllegalStateException("GET request cannot have a body");
                }
                if (timeout.isNegative() || timeout.isZero()) {
                    throw new IllegalStateException("timeout must be positive");
                }
                if (maxRetries < 0 || maxRetries > 5) {
                    throw new IllegalStateException("maxRetries must be between 0 and 5");
                }
                return new HttpRequest(this);
            }
        }
    }

    public static void main(String[] args) {
        // Only the required part: everything else falls back to defaults
        HttpRequest simple = HttpRequest.newBuilder("https://api.example.com/users").build();
        System.out.println("defaults:");
        System.out.println("  " + simple);

        // Readable alternative to a constructor with six positional arguments
        HttpRequest create = HttpRequest.newBuilder("https://api.example.com/users")
                .post("{\"name\":\"Omer\"}")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer token-123")
                .timeout(Duration.ofSeconds(10))
                .maxRetries(3)
                .build();
        System.out.println("\nfluent configuration:");
        System.out.println("  " + create);

        // toBuilder creates a new variant, the original stays untouched
        HttpRequest retried = create.toBuilder()
                .header("X-Request-Id", "retry-1")
                .maxRetries(5)
                .build();
        System.out.println("\nvariant via toBuilder:");
        System.out.println("  " + retried);
        System.out.printf("  original headers : %d, variant headers: %d%n",
                create.headers().size(), retried.headers().size());

        // Exposed collections are read-only
        try {
            create.headers().put("Hack", "true");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nimmutability:");
            System.out.println("  headers cannot be modified after build()");
        }

        // Invalid combinations are rejected in build()
        System.out.println("\nvalidation:");
        tryBuild(HttpRequest.newBuilder("ftp://files.example.com"));
        tryBuild(HttpRequest.newBuilder("https://api.example.com").get().body("not allowed"));
        tryBuild(HttpRequest.newBuilder("https://api.example.com").timeout(Duration.ZERO));
        tryBuild(HttpRequest.newBuilder("https://api.example.com").maxRetries(10));
    }

    private static void tryBuild(HttpRequest.Builder builder) {
        try {
            builder.build();
            System.out.println("  built successfully");
        } catch (IllegalStateException e) {
            System.out.println("  rejected: " + e.getMessage());
        }
    }
}
