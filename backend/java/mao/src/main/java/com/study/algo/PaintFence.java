package com.study.algo;
//https://leetcode.ca/2016-09-01-276-Paint-Fence/
public class PaintFence {
    //dp -> 种类
    public int numWays(int n, int k) {
        if (n == 0 || k == 0)
            return 0;

        // `dp[i][0]` same color
        // `dp[i][1]` different color
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = k;
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][1] * 1;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) * (k - 1); //可以看出dp是最终组合种类，通过dp[i-1]推出来（截止i-1的组合种类）
        }
        /*
        应该可以用三维数组求解
         */
        return dp[n - 1][0] + dp[n - 1][1];
    }
}
