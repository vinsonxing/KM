package com.study.algo;

//前缀和的灵活应用
public class MaxSumOfTwoNoOverlappingSubarray {
    public static int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int ans = 0;
        for (int i = firstLen, f = 0, e = 0; i + secondLen - 1 < n; ++i) {
            f = Math.max(f, s[i] - s[i - firstLen]);
            e = Math.max(e, s[i + secondLen] - s[i]);
            ans = Math.max(ans, f + e);
        }
        for (int i = secondLen, f = 0, e = 0; i + firstLen - 1 < n; ++i) {
            f = Math.max(f, s[i] - s[i - secondLen]);
            e = Math.max(e, s[i + firstLen] - s[i]);
            ans = Math.max(ans, f + e);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 6, 5, 2, 2, 5, 1, 9, 4};
        System.out.println(maxSumTwoNoOverlap(nums, 2, 1));
    }
}
