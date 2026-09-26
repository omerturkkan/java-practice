package io.github.omerturkkan.javapractice.exceptions.custom;

// Unchecked: a programming/input error the caller is not expected to recover from
public class InvalidCardException extends RuntimeException {
    public InvalidCardException(String message) {
        super(message);
    }
}
