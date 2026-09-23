package io.github.omerturkkan.javapractice.interfaces.multipleinheritance;

public interface Scanner {
    String scan();

    // Same signature as Printer.status(): the implementing class must resolve the clash
    default String status() {
        return "Scanner ready";
    }

    default int resolutionDpi() {
        return 600;
    }
}
