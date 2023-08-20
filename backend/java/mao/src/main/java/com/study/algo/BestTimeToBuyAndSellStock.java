package com.study.algo;
//https://leetcode.ca/2016-04-01-123-Best-Time-to-Buy-and-Sell-Stock-III/
//https://blog.csdn.net/zhi6fui/article/details/125232842

import java.util.Deque;
import java.util.LinkedList;

//dp[i][j]=Math.max(dp[i][j-1],prices[j]-prices[k]+dp[i-1][k-1]); i是次数 j是天数
public class BestTimeToBuyAndSellStock {
    static int getMostProfit(int[] prices) {
        int[][] dp = new int[3][prices.length];
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j < prices.length; j++) {
                for (int k = j - 1; k >= 0; k--) {
                    int max = 0;
                    if (k == 0) {
                        max = Math.max(dp[i][j - 1], prices[j] - prices[k]);//买第一个，这时只能时第一次够买
                    } else {
                        max = Math.max(dp[i][j - 1], prices[j] - prices[k] + dp[i - 1][k - 1]);//dp[i-1][k-1]上一次买到的
                    }
                    dp[i][j] = Math.max(dp[i][j], max);
                }
            }
        }
//        for(int i=0;i<=2;i++) {
//            for(int j=0;j<prices.length;j++) {
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }
        return dp[2][prices.length - 1];
    }

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(getMostProfit(prices));
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int[][] dp = new int[3][prices.length];
        for (int i = 1; i <= 2; i++) {
            int max = Integer.MIN_VALUE / 2;
            for (int j = 1; j < prices.length; j++) { //k 包含在j里
                max = Math.max(max, j == 1 ? -prices[j - 1] : dp[i - 1][j - 2] - prices[j - 1]);
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + max);
            }
        }
        return dp[2][prices.length - 1];
    }

}
