package com.study.algo;

//https://leetcode.ca/2022-12-02-2444-Count-Subarrays-With-Fixed-Bounds/
public class CountSubarrayWithFixedBounds {
    static int getCount(int[] arr, int min, int max) {
        int minIndex = -1, maxIndex = -1;
        int size = arr.length;
        int left = 0, right = 0;
        int ans = 0;
        while (right < size) {
            if (minIndex == -1 && arr[right] == min) {
                minIndex = right;
            }
            if (maxIndex == -1 && arr[right] == max) {
                maxIndex = right;
            }
            if (arr[right] > max || arr[right] < min) {
                left = right + 1;
                minIndex = -1;
                maxIndex = -1;
            } else {
                if (maxIndex != -1 && minIndex != -1) {
                    ans += Math.min(maxIndex, minIndex) - left + 1;
                }
            }
            right++;
        }
        return ans;
    }

    public static long countSubarrays(int[] nums, int minK, int maxK) {
        long ans = 0;
        int j1 = -1, j2 = -1, k = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < minK || nums[i] > maxK) {
                k = i;
            }
            if (nums[i] == minK) {
                j1 = i;
            }
            if (nums[i] == maxK) {
                j2 = i;
            }
            ans += Math.max(0, Math.min(j1, j2) - k);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 3, 5, 2, 7, 3, 1, 2, 5, 4};
        System.out.println(getCount(nums, 1, 5));
        System.out.println(countSubarrays(nums, 1, 5));
    }
}
