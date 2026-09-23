package io.github.omerturkkan.javapractice.interfaces.multipleinheritance;

// A class can extend only one class, but implement many interfaces
public class AllInOneDevice implements Printer, Scanner {
    private final String model;
    private int printedPages;

    public AllInOneDevice(String model) {
        this.model = model;
    }

    @Override
    public void print(String document) {
        printedPages++;
        System.out.printf("  printing '%s' (page %d)%n", document, printedPages);
    }

    @Override
    public String scan() {
        return String.format("scan-%03d.pdf at %d dpi", printedPages + 1, resolutionDpi());
    }

    // Both interfaces define status(), so the compiler forces an override here
    @Override
    public String status() {
        return model + " | " + Printer.super.status() + " + " + Scanner.super.status();
    }

    @Override
    public int resolutionDpi() {
        return 1200;   // overrides the interface default
    }
}
