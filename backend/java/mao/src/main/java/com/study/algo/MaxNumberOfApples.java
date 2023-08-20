package com.study.algo;

import java.util.Arrays;

//greedy
public class MaxNumberOfApples {
    static int CAPACITY = 5000;
    public int maxNumberOfApples(int[] arr) {
        Arrays.sort(arr);
        int totalWeight = 0, count = 0;
        int length = arr.length;
        for(int i=0; i<length; i++) {
            int weight = arr[i];
            if(totalWeight + weight <= CAPACITY) {
                totalWeight += weight;
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
