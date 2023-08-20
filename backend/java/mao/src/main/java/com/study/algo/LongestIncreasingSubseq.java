package com.study.algo;

import java.util.Arrays;

public class LongestIncreasingSubseq {
    public static int lengthOfLIS(int[] nums) {

        // store the increasing subsequence formed by including the currently encountered element
        int[] dp = new int[nums.length];

        int len = 0;
        for (int num : nums) {
            // Arrays.binarySearch() method returns index of the search key, if it is contained in the array,
            // else it returns (-(insertion point) - 1)
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }

            dp[i] = num;

            if (i == len) {
                len++;
            }
        }
        return len;
    }
    /*
    len[i] = 1 + max( len[j] | 0 <= j < i && A[j] < A[i] )
    cnt[i] = sum( cnt[j] | 0 <= j < i && len[j] + 1 == len[i] )
     */
    public int findNumberOfLIS(int[] nums) {
        int maxLen = 0, ans = 0, n = nums.length;
        int[] len = new int[n];
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            len[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) { //what a dp
                    if (len[j] + 1 > len[i]) { //不等式两边动态规划，序号大一边要初始化
                        len[i] = len[j] + 1;
                        cnt[i] = cnt[j];  //新的初始化
                    } else if (len[j] + 1 == len[i]) {//动态规划做个数统计，考虑累加
                        cnt[i] += cnt[j];
                    }
                }
            }
            if (len[i] > maxLen) {
                maxLen = len[i];
                ans = cnt[i];
            } else if (len[i] == maxLen) {
                ans += cnt[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10,11,2,5,3,7,101,18,8,9};
        System.out.println(lengthOfLIS(nums));
    }
}
