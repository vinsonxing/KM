package com.study.algo;

public class SearchInRotatedSortedArray {
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if(nums[left] <= nums[mid]) {
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }
        if (nums[left] == target && nums[right] == target){
            return left;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,3};
        System.out.println(search(nums, 3));
        System.out.println(search(nums, 5));
    }
}
