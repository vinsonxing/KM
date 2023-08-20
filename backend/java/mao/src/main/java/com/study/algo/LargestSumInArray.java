package com.study.algo;

public class LargestSumInArray {
    static int getLargestSum(int[] arr) {
        int size = arr.length;
        int[] dp = new int[size];
        dp[0] = arr[0];
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < size; i++) {
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {5, -6, 2, 2, 4, -5, 6, -1, -2};
        System.out.println(getLargestSum(arr));
    }
}
