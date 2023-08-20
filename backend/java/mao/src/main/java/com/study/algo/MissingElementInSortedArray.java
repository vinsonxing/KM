package com.study.algo;
//https://leetcode.ca/all/1060.html
public class MissingElementInSortedArray {
    public static int missingInnerElement(int[] nums, int k) {
        for(int i = 0; i < nums.length - 1; ) {
            int diff = nums[i + 1] - nums[i] - 1;
            if(k <= diff) {
                return nums[i] + k;
            } else {
                k -= diff;
                i++;
            }
        }
        return -1;
    }
}
