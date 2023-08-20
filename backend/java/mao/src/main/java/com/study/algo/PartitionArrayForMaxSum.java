package com.study.algo;

//todo
//https://leetcode.ca/2018-10-08-1043-Partition-Array-for-Maximum-Sum/
/*
Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.  After partitioning, each subarray has their values changed to become the maximum value of that subarray.
Return the largest sum of the given array after partitioning.
Input: A = [1,15,7,9,2,5,10], K = 3
Output: 84
Explanation: A becomes [15,15,15,9,10,10,10]

 */
public class PartitionArrayForMaxSum {
    public static int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] f = new int[n];
        for (int i = 0; i < n; ++i) {
            int mx = 0;
            for (int j = i; j >= Math.max(0, i - k + 1); --j) {
                mx = Math.max(mx, arr[j]);
                f[i] = Math.max(f[i], mx * (i - j + 1) + (j > 1 ? f[j - 1] : 0));
            }
        }
//        for (int i = 0; i < n; i++) {
//            System.out.println(f[i]);
//        }
        return f[n - 1];
    }

    public static int maxSumAfterPartitioning1(int[] arr, int k) {
        int n = arr.length;
        int[] f = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            int mx = 0;
            for (int j = i; j >= Math.max(0, i - k + 1); --j) {
                mx = Math.max(mx, arr[j]);
                int t = mx * (i - j + 1) + f[j];
                f[i + 1] = Math.max(f[i + 1], t);
            }
        }
//        for (int i = 0; i <= n; i++) {
//            System.out.println(f[i]);
//        }
        return f[n];
    }

    public static void main(String[] args) {
        int[] arr = {1, 15, 7, 9, 2, 5, 10};
        int k = 3;
        System.out.println(maxSumAfterPartitioning(arr, k));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(maxSumAfterPartitioning1(arr, k));
    }
}
