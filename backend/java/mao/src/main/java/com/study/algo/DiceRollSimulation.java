package com.study.algo;

//https://leetcode.ca/2019-04-06-1223-Dice-Roll-Simulation/
public class DiceRollSimulation {
    public static void main(String[] args) {
        int[] rollMax = {1, 1, 2, 2, 2, 2};
        System.out.println(dieSimulator(rollMax, 2));
    }

    public static int dieSimulator(int[] rollMax, int n) {
        final int MODULO = 1000000007;
        int[][][] dp = new int[n][6][15];
        for (int i = 0; i < 6; i++)
            dp[0][i][0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    if (k == j)
                        continue;
                    for (int m = 0; m < 15; m++)
                        dp[i][j][0] = (dp[i][j][0] + dp[i - 1][k][m]) % MODULO;
                }
                int max = rollMax[j];
                for (int k = 1; k < max; k++)
                    dp[i][j][k] = dp[i - 1][j][k - 1]; //重复了k次
            }
        }
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 15; j++)
                sum = (sum + dp[n - 1][i][j]) % MODULO;
        }
        return sum;
    }
    /*
    i 扫描长度 j 结尾字符 k j重复次数
     */
}
