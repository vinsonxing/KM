package com.study.algo;

import java.util.Arrays;
//识别左边界
//https://leetcode.ca/all/1300.html
public class SumOfMutatedArrayClosetToTarget {
    static int findBestValue(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int[] sum = new int[len];
        sum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        int left = target / len, right = nums[len - 1];
        int diff = Integer.MAX_VALUE;
        int ans = -1; //找最接近的位置还是要用一个变量存储，最接近可能不是单调的

        while (left < right) {
            int mid = (left + right) / 2;
            int index = Arrays.binarySearch(nums, 0, len, mid);
            if (index < 0) {
                index = -(index + 1);
            }
            int tmpSum = index == 0 ? len * mid : (len - index) * mid + sum[index - 1];
            if (tmpSum - target < 0) {
                if (target - tmpSum <= diff) {
                    diff = target - tmpSum;
                    ans = mid;
                }
                left = mid + 1;
            } else {
                if (tmpSum - target <= diff) {
                    diff = tmpSum - target;
                    ans = mid;
                }
                right = mid;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {60864, 25176, 27249, 21296, 20204};
        System.out.println(findBestValue(nums, 56803));
//        int[] nums = {4,9,3};
//        System.out.println(findBestValue(nums, 10));
    }
}
