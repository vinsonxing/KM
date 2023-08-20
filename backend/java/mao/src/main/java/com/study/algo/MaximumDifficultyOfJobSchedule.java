/*
这个没有二分的办法，因为不单调，只可以用dp
 */
package com.study.algo;
//https://leetcode.ca/2019-07-27-1335-Minimum-Difficulty-of-a-Job-Schedule/
//todo
import java.util.Arrays;

//dp[d][i] = min( dp[d-1][j-1] + mx[j][i] | d-1 <= j <= i )
public class MaximumDifficultyOfJobSchedule {
    /*递归有两种，一种是每一个都可以二分两种情况，可选可不选
    第二种就是递归是在for循环里面，每一个元素其实都需要，需要考虑过去的元素结合将来递归的结果全局求最优解
    */
    public static int minDifficulty(int[] jobDifficulty, int index, int left) {
        if (left == 1) {
            int max = 0;
            for (int i = index; i < jobDifficulty.length; i++) {
                max = Math.max(max, jobDifficulty[i]);
            }
            return max;
        }
        if (index == jobDifficulty.length) {
            return Integer.MAX_VALUE;
        }
//        int p1 = Integer.MAX_VALUE, p2 = Integer.MAX_VALUE;
//
//        //index 前一天做
//        int currmax = premax >= jobDifficulty[index] ? premax : jobDifficulty[index];
//        int next1 = minDifficulty(jobDifficulty, currmax, index + 1, left);
//        if(next1 != Integer.MAX_VALUE) {
//            p1 = currmax + next1;
//        }
//        //index 后一天做
//        int next2 = minDifficulty(jobDifficulty, jobDifficulty[index], index + 1, left - 1);
//        if (next2 != Integer.MAX_VALUE) {
//            p2 = premax + next2;
//        }
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = index; i < jobDifficulty.length; i++) {
            max = Math.max(max, jobDifficulty[i]);
            int next = minDifficulty(jobDifficulty, index + 1, left - 1);
            if (next != Integer.MAX_VALUE) {
                min = Math.min(min, max + next);
            }
        }
        return min;
    }

    public static int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        int inf = Integer.MAX_VALUE / 2;
        int[][] f = new int[n + 1][d + 1];
        /*
        记住一开始是fill 0 还是 fill 最大值，如果求最小就fill 1 << 30, 最大一般fill 0 就可以
         */
        for (int[] g : f) {
            Arrays.fill(g, inf);
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= d; j++) {
                int max = 0;
                for (int k = i; k > 0; k--) {
                    max = Math.max(max, jobDifficulty[k - 1]);
                    f[i][j] = Math.min(f[i][j], f[k - 1][j - 1] + max);
                }
            }
        }
        return f[n][d] < inf ? f[n][d] : -1;
    }

    public static void main(String[] args) {
        int[] jobDifficulty = {7, 1, 8, 2, 7, 1};
        int d = 3;
        System.out.println(minDifficulty(jobDifficulty, 3));
        System.out.println(minDifficulty(jobDifficulty, 0, 3));
    }
}
