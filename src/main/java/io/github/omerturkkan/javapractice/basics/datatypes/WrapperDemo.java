package io.github.omerturkkan.javapractice.basics.datatypes;

public class WrapperDemo {
    public static void main(String[] args) {
        int primitive = 42;
        Integer wrapper = primitive;
        int backToPrimitive = wrapper;

        System.out.printf("Autoboxing    : %d -> %s%n", primitive, wrapper.getClass().getSimpleName());
        System.out.printf("Unboxing      : %s -> %d%n", wrapper.getClass().getSimpleName(), backToPrimitive);

        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;

        System.out.printf("%na == b (127)  : %s%n", a == b);
        System.out.printf("c == d (128)  : %s%n", c == d);
        System.out.printf("c.equals(d)   : %s%n", c.equals(d));

        String numStr = "2048";
        int parsed = Integer.parseInt(numStr);
        double parsedDouble = Double.parseDouble("3.14");
        String backToStr = String.valueOf(parsed);

        System.out.printf("%nParsed int    : %d%n", parsed);
        System.out.printf("Parsed double : %.2f%n", parsedDouble);
        System.out.printf("Back to String: %s%n", backToStr);
    }
}