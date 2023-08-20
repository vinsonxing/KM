package com.study.algo;
//奇偶问题想两个集合，子序列dp
//https://www.bilibili.com/video/BV1SL411B71a?p=38&vd_source=d64b61673a1ff62f40f5f6a1f7f407bf
public class SumEven {
    int getCount(int[] nums, int k) {
        int n = nums.length;
        int[][] even = new int[n + 1][k + 1];
        int[][] odd = new int[n + 1][k + 1];
        for (int i = 0; i < n; i++) {
            even[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < Math.min(i, k); j++) {
                even[i][j] = even[i - 1][j];
                odd[i][j] = odd[i - 1][j];
                even[i][j] += (nums[i - 1] / 2 == 0 ? even[i - 1][j - 1] : odd[i - 1][j - 1]);
                odd[i][j] += (nums[i - 1] / 2 == 0 ? odd[i - 1][j - 1] : even[i - 1][j - 1]);
            }
        }
        return even[n][k];
    }
}
