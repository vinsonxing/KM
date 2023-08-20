package com.study.algo;

public class MinimumInRotatedSortedArray {
    static int findMin(int[] nums) {
        int n = nums.length;
        if (nums[0] <= nums[n - 1]) {
            return nums[0];
        }
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println("left=" + left + "; right=" + right);
        return nums[left];
    }

    static int findMax(int[] nums) {
        int n = nums.length;
        if (nums[0] <= nums[n - 1]) {
            return nums[n - 1];
        }
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[left] >= nums[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
            //如果按最小值做法对比右半区间，则取最大一定要把mid包含进来，left=mid有可能死循环
        }
        System.out.println("left=" + left + "; right=" + right);
        return nums[left - 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 6, 1, 2, 3, 4};
//        int[] nums = new int[]{1, 2};
        System.out.println("min:" + findMin(nums));
        System.out.println("max:" + findMax(nums));
    }
}
