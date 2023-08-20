package com.study.algo;

import java.util.Arrays;
//以i结尾
//https://leetcode.ca/all/1186.html
public class MaximumSubarraySumWithOneDeletion {
    static int maximumSum(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][2];
        dp[0][0] = arr[0];
        dp[0][1] = 1 >> 30;
        int ans = arr[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(arr[i], dp[i - 1][0] + arr[i]); // arr[i]这个可能性容易忽略
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]); 
            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
        }
        return ans;
    }

//    static int maximumSum1(int[] arr) {
//        int n = arr.length;
//        int[] dp = new int[n];
//        int[] presum = new int[n];
//        dp[0] = arr[0];
//        presum[0] = arr[0];
//        for (int i = 1; i < n; i++) {
//            presum[i] = presum[i-1] + arr[i];
//            if (arr[i] < 0) {
//                dp[i] = Math.max(presum[i - 1], dp[i - 1]);
//            } else {
//                dp[i] = dp[i - 1] + arr[i];
//            }
//        }
//        return dp[n - 1];
//    }

    public static int maximumSum2(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int t = 0;
        for (int i = 0; i < n; ++i) {
            t = Math.max(t, 0) + arr[i];
            left[i] = t;
        }
        t = 0;
        for (int i = n - 1; i >= 0; --i) {
            t = Math.max(t, 0) + arr[i];
            right[i] = t;
        }
        int ans = Arrays.stream(left).max().getAsInt();
        for (int i = 1; i < n - 1; ++i) {
            ans = Math.max(ans, left[i - 1] + right[i + 1]);
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[] arr = {3, -1, -1, 2};
        int[] arr = {1, -2, 3, -2};
        System.out.println(maximumSum(arr));
//        System.out.println(maximumSum1(arr));
        System.out.println(maximumSum2(arr));
    }
}
