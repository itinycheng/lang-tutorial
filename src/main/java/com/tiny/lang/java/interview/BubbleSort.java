package com.tiny.lang.java.interview;

import java.util.Arrays;

/**
 * @author tiny
 */
public class BubbleSort {
    public static void main(String[] args) {
        var array = new int[]{1, 3, 8, 5};
        sort1(array);
        System.out.println(Arrays.toString(array));

    }

    private static void sort1(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
            }
            swap(array, i, min);
        }
    }

    private static void swap(int[] array, int i, int min) {
        int temp = array[i];
        array[i] = array[min];
        array[min] = temp;
    }
}
