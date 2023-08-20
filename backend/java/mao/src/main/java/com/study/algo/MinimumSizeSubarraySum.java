package com.study.algo;
//https://leetcode.ca/all/209.html
//滑动窗口
public class MinimumSizeSubarraySum {
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0;
        int n = nums.length;
        int res = n + 1;
        int sum = 0;
        while (right < n) {
            sum += nums[right];
            while(sum >= target){ //如果要压缩窗口，就在循环里求res
                res = Math.min(res, right - left +1);
//                print(nums, left, right);
//                System.out.println();
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return res;
    }
    public static void main(String[] args) {
        int[] nums = new int[] {1,3,1,2,4,3};
        System.out.println(minSubArrayLen(9, nums));
    }

    public static void print(int[] nums, int left, int right){
        for(int i = left; i <= right; i++){
            System.out.print(nums[i] + " ");
        }
    }
}
