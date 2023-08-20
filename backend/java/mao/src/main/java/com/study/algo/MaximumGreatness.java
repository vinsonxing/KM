package com.study.algo;

import java.util.Arrays;

public class MaximumGreatness {
    public static int getMaxGreat(int[] arr) {
        int great = 0;
        Arrays.sort(arr);
        //int[] arr_clone = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    swap(i, j, arr);
                    great++;
                    break;
                }
            }
        }
        return great;
    }

    public static void main(String[] args) {
        int[] arr= {1, 3, 5, 2, 1, 3, 1};
        System.out.println(getMaxGreat(arr));
    }

    private static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
