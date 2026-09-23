package io.github.omerturkkan.javapractice.interfaces.multipleinheritance;

public class DeviceDemo {
    public static void main(String[] args) {
        AllInOneDevice device = new AllInOneDevice("HP LaserJet 4200");

        System.out.println(device.status());
        device.print("invoice.pdf");
        device.print("contract.docx");
        System.out.println("  " + device.scan());
        System.out.println("  supplies: " + device.supplies());   // inherited, not overridden

        // The same object seen through two different interfaces
        Printer asPrinter = device;
        Scanner asScanner = device;

        System.out.printf("%nAs Printer : %s%n", asPrinter.status());
        System.out.printf("As Scanner : %s%n", asScanner.status());
        System.out.println("Both references point to the same object: " + (asPrinter == asScanner));
        System.out.printf("Resolution : %d dpi (default was 600)%n", asScanner.resolutionDpi());
    }
}
