package com.tiny.lang.java.interview;

/**
 * @author tiny
 */
public class MatrixSearch {

    private static final int[][] MATRIX = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24}
    };

    static void find(int sed) {
        int i = 0;
        int j = MATRIX[0].length - 1;
        while (i >= 0 && j >= 0
                && i < MATRIX.length
                && j < MATRIX[0].length) {
            if (MATRIX[i][j] > sed) {
                j--;
            } else if (MATRIX[i][j] < sed) {
                i++;
            } else {
                System.out.println("i = " + i + ", j = " + j);
                return;
            }
        }
        System.out.println("not found");
    }

    public static void main(String[] args) {
        find(1);
        find(14);
        find(24);
        find(18);
    }
}
