package com.study.algo;

//f(n)=2f(n-1)-f(n-m-1)
public class HopOnStep {
    public static int hopWays(int total, int max) {
        int[] dp = new int[total + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= total; i++) {
            dp[i] = 2 * dp[i - 1] - (i - max - 1 < 0 ? 0 : dp[i - max - 1]);
        }
        return dp[total];
    }

    public static int hopWays1(int total) {
        int[] dp = new int[total + 1];
        dp[0] = 1;
        dp[1] = 1;
        // dp[2] = 2;
        for (int i = 2; i <= total; i++) {
            dp[i] = 2 * dp[i - 1];
        }
        return dp[total];
    }

    public static void main(String[] args) {
        System.out.println(hopWays(5, 5));
        System.out.println(hopWays1(5));
    }
}
