package com.study.algo;
//很新颖
public class MinimizeMaximumOfArray {
    private int[] nums;

    public int minimizeArrayValue(int[] nums) {
        this.nums = nums;
        int left = 0, right = max(nums);
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int mx) {
        long buffer = 0;
        for (int i = nums.length - 1; i > 0; --i) {
            buffer = Math.max(0, buffer + nums[i] - mx);
        }
        return nums[0] + buffer <= mx;
    }

    private int max(int[] nums) {
        int v = nums[0];
        for (int x : nums) {
            v = Math.max(v, x);
        }
        return v;
    }
}
