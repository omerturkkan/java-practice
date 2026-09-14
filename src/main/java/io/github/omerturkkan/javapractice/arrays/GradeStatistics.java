package io.github.omerturkkan.javapractice.arrays;

public class GradeStatistics {
    public static void main(String[] args) {
        int[] grades = {72, 85, 90, 65, 95, 88, 76, 100, 55, 82};

        int sum = 0;
        int min = grades[0];
        int max = grades[0];
        int passCount = 0;

        for (int grade : grades) {
            sum += grade;
            if (grade < min) min = grade;
            if (grade > max) max = grade;
            if (grade >= 60) passCount++;
        }

        double average = (double) sum / grades.length;
        int failCount = grades.length - passCount;

        System.out.printf("Students : %d%n", grades.length);
        System.out.printf("Average  : %.1f%n", average);
        System.out.printf("Highest  : %d%n", max);
        System.out.printf("Lowest   : %d%n", min);
        System.out.printf("Passed   : %d%n", passCount);
        System.out.printf("Failed   : %d%n", failCount);
    }
}