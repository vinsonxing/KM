package com.study.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//独立做一遍
public class MinimumOperationToMakeArrayKIncreasing {
    public static void main(String[] args) {
//        int[] nums = {4, 1, 5, 4, 6, 2, 3};
//        System.out.println(KIncreasing(nums, 3));
        int[] nums = {1,2,3,3};
        System.out.println(LIS(nums, nums.length));
        System.out.println(LIS1(nums, nums.length));
    }

    static int KIncreasing(int[] nums, int k) {
        int ans = 0;
        int len = nums.length;
        for (int i = 0; i < k; i++) {
            int size = 0;
            int[] tmp = new int[len];
            for (int j = i; j < len; j += k) {
                tmp[size++] = nums[j]; //size ！= len 初始长度和有数据长度不一样
            }
            ans += size - LIS1(tmp, size);
        }
        return ans;
    }

    static private int LIS(int[] nums, int size) {
        int len = 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < size; i++) {
            int num = nums[i];
            int l = 0, r = len;
            while (l < r) {
                int mid = (l + r) / 2;
                if (dp[mid] <= num) {  //等号位置决定插入位置
                    l = mid + 1;
                } else {
                    r = mid;
                }
//                if (dp[mid] >= num) {
//                    r = mid;
//                } else {
//                    l = mid + 1;
//                }
            }
            dp[l] = num;
            if (l == len) {
                len++;
            }
        }
        return len;
    }

    static private int LIS1(int[] nums, int size) {
        List<Integer> tails = new ArrayList<>();
        tails.add(nums[0]);
        for (int i = 1; i < size; i++) {
            int num = nums[i];
            if (num > tails.get(tails.size() - 1)) {
                tails.add(num);
            } else {
                int index = Arrays.binarySearch(tails.toArray(), 0, tails.size() - 1, num);
                tails.set(-(index + 1), num);
            }
        }
        return tails.size();
    }
}
