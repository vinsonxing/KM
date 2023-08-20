package com.study.algo;
//https://leetcode.ca/2021-05-15-1793-Maximum-Score-of-a-Good-Subarray/

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

//单调栈
public class MaximumScoreOfAGoodSubarray {
    public static int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1); //注意初始值
        Arrays.fill(right, n);
        Deque<Integer> stk = new ArrayDeque<>();
        /*
        找到第一个比当前数小的，那么中间下标都是比当前数大的，扩区间目标达成
         */
        /*
        以当前数为最大值，向左向右最多能扩到多少
         */
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            while (!stk.isEmpty() && nums[stk.peek()] >= v) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            int v = nums[i];
            while (!stk.isEmpty() && nums[stk.peek()] > v) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (left[i] + 1 <= k && k <= right[i] - 1) {
                ans = Math.max(ans, nums[i] * (right[i] - 1 - (left[i] + 1) - 1));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {0, 5, 2, 3};
        System.out.println(maximumScore(nums, 3));
    }
}
