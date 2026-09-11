package io.github.omerturkkan.javapractice.basics.operators;

public class TimeConverter {
    public static void main(String[] args) {
        int totalSeconds = 86399;

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        System.out.printf("Total seconds : %d%n", totalSeconds);
        System.out.printf("Formatted     : %02d:%02d:%02d%n", hours, minutes, seconds);

        int backToSeconds = hours * 3600 + minutes * 60 + seconds;
        boolean verified = backToSeconds == totalSeconds;

        System.out.printf("Back to sec   : %d%n", backToSeconds);
        System.out.printf("Verified      : %s%n", verified);
    }
}