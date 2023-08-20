package com.study.algo;

import java.util.Arrays;

//任意选取可以先排序试试看

public class ThreeSumSmaller {
    public static int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int low = i + 1, high = nums.length - 1;
            while (low < high) {
                if (nums[i] + nums[low] + nums[high] < target) {
                    count++;
                    low++;
                } else {
                    high--;
                }
            }
        }
        return count;
    }
    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE;
        int sum = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int low = i + 1, high = nums.length - 1;
            while (low < high) {
                int tempSum = nums[i] + nums[low] + nums[high];
                if (tempSum < target) {
                    if(target - tempSum < closest) {
                        closest = target - tempSum;
                        sum = tempSum;
                    }
                    low++;
                } else {
                    if(tempSum - target < closest) {
                        closest = tempSum - target;
                        sum = tempSum;
                    }
                    high--;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        System.out.println(threeSumClosest(nums, target));
    }
}
