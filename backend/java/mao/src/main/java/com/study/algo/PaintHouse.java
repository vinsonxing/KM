package com.study.algo;

import java.util.Arrays;

// https://leetcode.ca/2016-08-21-265-Paint-House-II/
/*
非典型做法 第二个维度代表不同含义
 */
public class PaintHouse {
//    static int getMinCost(int[][] cost) {
//        int house = cost.length;
//        int color = cost[0].length;
//        int[][] dp = new int[house][2];
//        int min = Integer.MAX_VALUE;
//        int index = -1;
//        for (int i = 0; i < house; i++) {
//            if (cost[0][i] < min) {
//                min = cost[0][i];
//                index = i;
//            }
//        }
//        dp[0][0] = index;
//        dp[0][1] = min;
//        for (int i = 1; i < house; i++) {
//            min = Integer.MAX_VALUE;
//            index = -1;
//            for (int j = 0; j < color; j++) {
//                if (cost[i][j] < min && j != dp[i - 1][0]) {
//                    min = cost[i][j];
//                    index = j;
//                }
//            }
//            dp[i] = new int[]{index, dp[i - 1][1] + min};
//        }
//        return dp[house - 1][1];
//    }

    public static int minCostII_1(int[][] costs) {
        int house = costs.length;
        int color = costs[0].length;
        int[][] dp = new int[house][color];
        int inf = Integer.MAX_VALUE/2;
        for (int[] d : dp) {
            Arrays.fill(d, inf);
        }
        for (int i = 0; i < color; i++) {
            dp[0][i] = costs[0][i];
        }
        int min = Integer.MAX_VALUE/2;
        int minsec = Integer.MAX_VALUE/2;
        int mincolor = -1;
        for (int i = 0; i < house; i++) {
            int tempmin = Integer.MAX_VALUE/2;
            int tempsec = Integer.MAX_VALUE/2;
            int tempcolor = -1;
            for (int j = 0; j < color; j++) {

//                for (int k = 0; k < color; k++) {
//                    if (j != k) {
//                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + costs[i][j]);
//                    }
//                }
                dp[i][j] = Math.min(dp[i][j], (j != mincolor? min : minsec) + costs[i][j]);
                tempmin = Math.min(min, dp[i][j]);
                if(min > dp[i][j]) {
                    tempmin = dp[i][j];
                    tempsec = tempmin;
                    tempcolor = j;
                } else if (minsec > dp[i][j]) {
                    tempsec = dp[i][j];
                }
            }
            min = tempmin;
            minsec = tempsec;
            mincolor = tempcolor;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < color; i++) {
            ans = Math.min(ans, dp[house - 1][i]);
        }
        return ans;
    }

    public static int minCostII(int[][] costs) {
        int house = costs.length;
        int color = costs[0].length;
        int[][] dp = new int[house][color];
        int inf = 1 << 30;
        for (int[] d : dp) {
            Arrays.fill(d, inf);
        }
        for (int i = 0; i < color; i++) {
            dp[0][i] = costs[0][i];
        }
        for (int i = 1; i < house; i++) {
            for (int j = 0; j < color; j++) {
                for (int k = 0; k < color; k++) {
                    if (j != k) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + costs[i][j]);
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < color; i++) {
            ans = Math.min(ans, dp[house - 1][i]);
        }
        return ans;
    }

    public static int minCostII_optimize(int[][] costs) {
        int house = costs.length;
        int color = costs[0].length;
        int minn = Integer.MAX_VALUE;
        int min_color = -1;
        int minsec = Integer.MAX_VALUE;
        for (int i = 0; i < house; i++) {
            int tempmin = Integer.MAX_VALUE;
            int tempcolor = -1;
            int tempminsec = Integer.MAX_VALUE;

            int tempcost = 0;

            for (int j = 0; j < color; j++) {
                tempcost = costs[i][j];
                if (i != 0) {
                    tempcost += (j == min_color) ? minsec : minn;
                }
                if (tempcost < tempmin) {
                    tempminsec = tempmin;
                    tempmin = tempcost;
                    tempcolor = j;
                } else if (tempcost < tempminsec) {
                    tempminsec = tempcost;
                }
            }
            minn = tempmin;
            min_color = tempcolor;
            minsec = tempminsec;
        }

        return minn;
    }

    public static void main(String[] args) {
        int[][] cost = {{1, 5, 3}, {2, 900, 400}};
        //System.out.println(getMinCost(cost)); //有问题
        System.out.println(minCostII(cost));
        System.out.println(minCostII_1(cost));
        System.out.println(minCostII_optimize(cost));
    }
}
