package com.study.algo;
//https://github.com/wisdompeak/LeetCode/tree/master/Greedy/2216.Minimum-Deletions-to-Make-Array-Beautiful
//https://leetcode.ca/2022-04-18-2216-Minimum-Deletions-to-Make-Array-Beautiful/
public class MinDeletionToMakeEvenUnequalToOdd {
    public int minDeletion(int[] nums) {
        int ans = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                ans++;
            } else {
                // 加上for循环后面的i++，相当于跳了两次
                // 正好是下一次删除后的偶数位置
                i++;
            }
        }
        if ((nums.length - ans) % 2 == 1) {
            ans++;
        }
        return ans;
    }
}


