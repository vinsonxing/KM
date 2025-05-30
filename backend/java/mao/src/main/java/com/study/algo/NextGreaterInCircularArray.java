package com.study.algo;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
//单调栈 -》 next greater , 存下标
public class NextGreaterInCircularArray {
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < (n << 1); ++i) {
            while (!stk.isEmpty() && nums[stk.peek()] < nums[i % n]) {
                ans[stk.pop()] = nums[i % n];
            }
            stk.push(i % n);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1};
        System.out.println(Arrays.toString(nextGreaterElements(nums)));
    }
}
