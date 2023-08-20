package com.study.algo;

import java.util.Arrays;

//https://leetcode.ca/2020-03-22-1574-Shortest-Subarray-to-be-Removed-to-Make-Array-Sorted/
public class RemoveShortestSubarrayToMakeArraySorted {
    static int removeSubarray(int[] nums) {
        int ans = Integer.MAX_VALUE;
        int left = 0, right = nums.length;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i - 1] > nums[i]) {
                left = i;
                break;
            }
        }
        for (int j = nums.length - 1; j > 0; j--) {
            if (nums[j - 1] > nums[j]) {
                right = j - 1;
                break;
            }
        }
        if (left > right) {
            return 0;
        }
//        System.out.println("left=" + left + " right=" + right);
//        int index1 = getIndex(nums, 0, left - 1, nums[right + 1]);
//        int leftRemCount = Math.abs(index1 - left);
//        int index2 = getIndex(nums, right + 1, nums.length, nums[left - 1]);
//        int rightRemCount = Math.abs(index2 - right - 1);
//        System.out.println("index1=" + index1 + " " + "index2=" + index2 + " " + "leftRemCount=" + leftRemCount + " rightRemCount=" + rightRemCount);
//        return right - left + 1 + Math.min(leftRemCount, rightRemCount);
        //双指针,也是一种滑动窗口，本质定一个动一个
//        for (int l = 0, r = right; l <= left; l++) {
//            while (r < nums.length && nums[r] < nums[l]) {
//                r++;
//            }
//            ans = Math.min(ans, r - l + 1);
//        }
        for (int l = 0, r = right; r < nums.length; r++) {
            while (l < left && nums[r] > nums[l]) {
                l++;
            }
            ans = Math.min(ans, r - l + 1);
        }
        return ans;
    }

    public static int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int i = 0, j = n - 1;
        while (i + 1 < n && arr[i] <= arr[i + 1]) {
            ++i;
        }
        while (j - 1 >= 0 && arr[j - 1] <= arr[j]) {
            --j;
        }
        if (i >= j) {
            return 0;
        }
        int ans = Math.min(n - i - 1, j);
        //最大滑动窗口定左移右，最小滑动窗口定右移左
        for (int l = 0, r = j; l <= i; ++l) {
            while (r < n && arr[r] < arr[l]) {
                ++r;
            }
            ans = Math.min(ans, r - l - 1);
        }
//        for (int l = 0, r = j; r < n; ++r) {
//            while (l <= i && arr[r] < arr[l]) {
//                ++l;
//            }
//            ans = Math.min(ans, r - l - 1);
//        }
        return ans;
    }

    private static int getIndex(int[] nums, int start, int end, int target) {
        int i = start, j = end;
        int ans = -1;
        while (i < j) {
            int mid = (i + j) / 2;
            if (nums[mid] <= target) {  //注意等号 这样可以保证index在后
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return i;
    }

    public static void main(String[] args) {
//        int[] nums = {1, 1, 4, 6, 7};
//        System.out.println(getIndex(nums, 1, nums.length, 5));
//        System.out.println(Arrays.binarySearch(nums, 0, nums.length, 1));
        int[] nums = {1, 2, 3, 10, 4, 3, 1, 3, 5};
//        int[] nums = {5, 4, 3, 2, 1};
        System.out.println(removeSubarray(nums));
        System.out.println(findLengthOfShortestSubarray(nums));
    }
}
