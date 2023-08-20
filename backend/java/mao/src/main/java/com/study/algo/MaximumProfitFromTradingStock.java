package com.study.algo;
//背包

//TODO - 转递归试试
//https://leetcode.ca/2022-07-02-2291-Maximum-Profit-From-Trading-Stocks/
public class MaximumProfitFromTradingStock {
    public static int maximumProfit(int[] present, int[] future, int budget) {
        int n = present.length;
        int[] f = new int[budget + 1];
        for (int i = 0; i < n; ++i) {
            int a = present[i], b = future[i];
//                for (int j = budget; j >= a; --j) {
            for (int j = a; j <= budget; ++j) {
                f[j] = Math.max(f[j], f[j - a] + b - a);
            }
        }
        return f[budget];
    }

    public static void main(String[] args) {
        int[] present = {5, 4, 6, 2, 3};
        int[] future = {8, 5, 4, 3, 5};
        int budget = 10;
        System.out.println(maximumProfit(present, future, budget));
    }
}
