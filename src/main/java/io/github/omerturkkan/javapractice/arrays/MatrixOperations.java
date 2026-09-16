package io.github.omerturkkan.javapractice.arrays;

public class MatrixOperations {
    public static void main(String[] args) {
        int[][] matrix = {
                {3, 7, 1},
                {9, 2, 6},
                {4, 8, 5}
        };

        int total = 0;
        int rowCount = matrix.length;
        int colCount = matrix[0].length;

        System.out.println("Matrix:");
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
                total += val;
            }
            System.out.println();
        }

        int diagonalSum = 0;
        for (int i = 0; i < rowCount; i++) {
            diagonalSum += matrix[i][i];
        }

        int[] rowSums = new int[rowCount];
        int[] colSums = new int[colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                rowSums[i] += matrix[i][j];
                colSums[j] += matrix[i][j];
            }
        }

        System.out.printf("%nTotal        : %d%n", total);
        System.out.printf("Diagonal sum : %d%n", diagonalSum);

        for (int i = 0; i < rowCount; i++) {
            System.out.printf("Row %d sum    : %d%n", i, rowSums[i]);
        }
        for (int j = 0; j < colCount; j++) {
            System.out.printf("Col %d sum    : %d%n", j, colSums[j]);
        }
    }
}