package com.study.algo;
//子数组和想前缀和
import java.util.HashMap;
import java.util.Map;
//https://leetcode.ca/2016-10-20-325-Maximum-Size-Subarray-Sum-Equals-k/
public class MaximumSubarraySumEqualK {
    public static int maxSubArrayLen(int[] nums, int k) {
        int max = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                max = Math.max(max, i + 1);
            }
            int diff = sum - k;
            if (map.containsKey(diff)) {
                max = Math.max(max, i - map.get(diff));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, -1, 5, -2, 3};
        System.out.println(maxSubArrayLen(array, 2));
    }
}
