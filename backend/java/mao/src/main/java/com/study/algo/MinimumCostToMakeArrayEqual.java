package com.study.algo;

import java.util.Arrays;

public class MinimumCostToMakeArrayEqual {
    public static long minCost(int[] nums, int[] cost) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{nums[i], cost[i]};
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        int[] left = new int[n];
        int presumCost = arr[0][1];
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] + presumCost * (arr[i][0] - arr[i - 1][0]);
            presumCost += arr[i][1];
        }
        int[] right = new int[n];
        presumCost = arr[n - 1][1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] + presumCost * (arr[i + 1][0] - arr[i][0]);
            presumCost += arr[i][1];
        }
        int[] all = new int[n];
        for (int i = 0; i < n; i++) {
            all[i] = left[i] + right[i];
        }
        return Arrays.stream(all).min().getAsInt();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 5};
        int[] cost = {2, 14, 3, 1};
        System.out.println(minCost(nums, cost));
    }

}
