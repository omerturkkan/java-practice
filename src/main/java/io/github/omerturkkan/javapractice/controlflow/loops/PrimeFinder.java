package io.github.omerturkkan.javapractice.controlflow.loops;

public class PrimeFinder {
    public static void main(String[] args) {
        int limit = 50;

        // for + continue + break: collect primes up to limit
        StringBuilder primes = new StringBuilder();
        int primeCount = 0;

        for (int n = 2; n <= limit; n++) {
            boolean isPrime = true;
            for (int d = 2; d * d <= n; d++) {
                if (n % d == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (!isPrime) continue;

            primes.append(n).append(' ');
            primeCount++;
        }

        // while: sum of digits
        int number = 98765;
        int temp = number;
        int digitSum = 0;
        while (temp > 0) {
            digitSum += temp % 10;
            temp /= 10;
        }

        // do-while: runs at least once, even for 0
        int value = 0;
        int digitCount = 0;
        do {
            digitCount++;
            value /= 10;
        } while (value != 0);

        // labeled break: first pair (a, b) where a and b are both prime and a + b == 40
        int pairA = -1;
        int pairB = -1;
        outer:
        for (int a = 2; a < 40; a++) {
            for (int b = a; b < 40; b++) {
                if (a + b == 40 && isPrime(a) && isPrime(b)) {
                    pairA = a;
                    pairB = b;
                    break outer;
                }
            }
        }

        System.out.printf("Primes up to %d   : %s%n", limit, primes.toString().trim());
        System.out.printf("Prime count       : %d%n", primeCount);
        System.out.printf("Digit sum of %d : %d%n", number, digitSum);
        System.out.printf("Digit count of 0  : %d%n", digitCount);
        System.out.printf("First prime pair  : %d + %d = 40%n", pairA, pairB);
    }

    private static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int d = 2; d * d <= n; d++) {
            if (n % d == 0) return false;
        }
        return true;
    }
}
