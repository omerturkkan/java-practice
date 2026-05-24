package io.github.omerturkkan.javapractice.basics.strings;

public class CaesarCipher {

    public static void main(String[] args) {
        String message = "Hello World";
        int shift = 3;

        String encrypted = encrypt(message, shift);
        String decrypted = encrypt(encrypted, -shift);

        System.out.println("Original  : " + message);
        System.out.println("Encrypted : " + encrypted);
        System.out.println("Decrypted : " + decrypted);
    }

    static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isUpperCase(c)) {
                char shifted = (char) (((c - 'A' + shift) % 26 + 26) % 26 + 'A');
                result.append(shifted);
            } else if (Character.isLowerCase(c)) {
                char shifted = (char) (((c - 'a' + shift) % 26 + 26) % 26 + 'a');
                result.append(shifted);
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}