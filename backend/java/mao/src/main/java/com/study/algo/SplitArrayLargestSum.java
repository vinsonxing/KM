package com.study.algo;
//看到最值先想二分解空间
//https://leetcode.ca/all/410.html
public class SplitArrayLargestSum {
    public static void main(String[] args) {

    }

    public static int splitArray(int[] nums, int k) {
        int left = 0, right = 0;
        for (int x : nums) {
            left = Math.max(left, x);
            right += x;
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(nums, mid, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean check(int[] nums, int mx, int k) {
        int s = 0, cnt = 0;
        for (int x : nums) {
            s += x;
            if (s > mx) {
                ++cnt;
                s = x;
            }
        }
        return cnt <= k;
    }

    public int splitArray_DP(int[] nums, int m) {
        int length = nums.length;
        int[][] dp = new int[length + 1][m + 1];
        int[] subSum = new int[length + 1];
        for (int i = 0; i <= length; i++) {
            for (int j = 0; j <= m; j++)
                dp[i][j] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < length; i++) {
            subSum[i + 1] = subSum[i] + nums[i];
        }
        dp[0][0] = 0;
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 0; k < i; k++)
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], subSum[i] - subSum[k]));
            }
        }
        return dp[length][m];
    }
}
