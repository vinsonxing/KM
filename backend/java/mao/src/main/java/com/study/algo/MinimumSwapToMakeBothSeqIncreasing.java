package com.study.algo;

//https://leetcode.ca/all/801.html
public class MinimumSwapToMakeBothSeqIncreasing {
    static int getSwap(int[] A, int[] B) {
        int n = A.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        int max = Integer.MAX_VALUE;
        //换不换都行，必须换，必须不换
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1] && B[i] > B[i - 1] && A[i] > B[i - 1] && B[i] > A[i - 1]) {
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = dp[i][0] + 1;
            } else if (A[i] > A[i - 1] && B[i] > B[i - 1] && (A[i] < B[i - 1] || B[i] < A[i - 1])) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + 1;
            } else {
                dp[i][0] = dp[i - 1][1];
                dp[i][1] = dp[i - 1][0] + 1;
            }
        }

        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }

    public static void main(String[] args) {
        int[] A = {1, 3, 5, 4};
        int[] B = {1, 2, 3, 7};
        System.out.println(getSwap(A, B));
    }
}
